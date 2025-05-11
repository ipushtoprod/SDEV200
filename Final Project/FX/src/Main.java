import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    private boolean gameActive = false;
    private Grid opponentGrid, userGrid;

    private int shipsToPosition = 5;

    private boolean opponentsTurn = false;

    private ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();

    private Parent buildInterface() {
        BorderPane mainContainer = new BorderPane();
        mainContainer.setPrefSize(1000, 1000);

        //instructions for the game
        Label instructions = new Label(" Guide: \n The top board is the enemy side, the bottom board is your side. \n To start, you must place your own ships on the bottom board. \n Left click will place a ship from left to right, Right click will place a ship from top to bottom. \n The game will start when all 5 ships are placed.");
        instructions.setAlignment(Pos.CENTER);
        mainContainer.setTop(instructions);

        //creating the initial grid for the opponent AI, some logic for turns and victory/loss conditions
        opponentGrid = new Grid(true, event -> {
            if (!gameActive)
                return;

            Grid.Tile tile = (Grid.Tile) event.getSource();
            if (tile.isHit)
                return;

            opponentsTurn = !tile.fireAt();

            if (opponentGrid.remainingShips == 0) {
                System.out.println("VICTORY");
                System.exit(0);
            }

            if (opponentsTurn)
                executeOpponentMove();
        });
    
        //see above, but for the player
        userGrid = new Grid(false, event -> {
            if (gameActive)
                return;

            Grid.Tile tile = (Grid.Tile) event.getSource();
            if (userGrid.placeShip(new Ship(shipsToPosition, event.getButton() == MouseButton.PRIMARY), tile.col, tile.row)) {
                if (--shipsToPosition == 0) {
                    initiateGame();
                }
            }
        });

        VBox gridContainer = new VBox(60, opponentGrid, userGrid);
        gridContainer.setAlignment(Pos.CENTER);

        mainContainer.setCenter(gridContainer);

        return mainContainer;
    }

    // executes when the player makes a move. opponent will immediately make a move after you make a move, more logic
    private void executeOpponentMove() {
        while (opponentsTurn) {
            int col = randomGenerator.nextInt(10);
            int row = randomGenerator.nextInt(10);

            Grid.Tile tile = userGrid.getTile(col, row);
            if (tile.isHit) {
                continue;
            }

            opponentsTurn = tile.fireAt();

            if (userGrid.remainingShips == 0) {
                System.out.println("DEFEAT");
                System.exit(0);
            }
        }
    }

    // starting the game, creates random ship locations for the opponent, very brute forced
    private void initiateGame() {
        int shipInstance = 5;

        while (shipInstance > 0) {
            int col = randomGenerator.nextInt(10);
            int row = randomGenerator.nextInt(10);

            boolean horizontal = randomGenerator.nextDouble() < 0.5;
            if (opponentGrid.placeShip(new Ship(shipInstance, horizontal), col, row)) {
                shipInstance--;
            }
        }
        gameActive = true;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(buildInterface());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
