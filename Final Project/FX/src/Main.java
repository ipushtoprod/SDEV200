import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean gameActive = false;
    private GameGrid opponentGrid, userGrid;

    private int vesselsToPosition = 5;

    private boolean opponentsTurn = false;

    private ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();

    private Parent buildInterface() {
        BorderPane mainContainer = new BorderPane();
        mainContainer.setPrefSize(650, 850);

        opponentGrid = new GameGrid(true, event -> {
            if (!gameActive)
                return;

            GameGrid.Tile tile = (GameGrid.Tile) event.getSource();
            if (tile.isHit)
                return;

            opponentsTurn = !tile.fireAt();

            if (opponentGrid.remainingVessels == 0) {
                System.out.println("VICTORY");
                System.exit(0);
            }

            if (opponentsTurn)
                executeOpponentMove();
        });

        userGrid = new GameGrid(false, event -> {
            if (gameActive)
                return;

            GameGrid.Tile tile = (GameGrid.Tile) event.getSource();
            if (userGrid.positionVessel(new Vessel(vesselsToPosition, event.getButton() == MouseButton.PRIMARY), tile.col, tile.row)) {
                if (--vesselsToPosition == 0) {
                    initiateGame();
                }
            }
        });

        VBox gridContainer = new VBox(60, opponentGrid, userGrid);
        gridContainer.setAlignment(Pos.CENTER);

        mainContainer.setCenter(gridContainer);

        return mainContainer;
    }

    private void executeOpponentMove() {
        while (opponentsTurn) {
            int col = randomGenerator.nextInt(12);
            int row = randomGenerator.nextInt(12);

            GameGrid.Tile tile = userGrid.getTile(col, row);
            if (tile.isHit)
                continue;

            opponentsTurn = tile.fireAt();

            if (userGrid.remainingVessels == 0) {
                System.out.println("DEFEAT");
                System.exit(0);
            }
        }
    }

    private void initiateGame() {
        int vesselType = 5;

        while (vesselType > 0) {
            int col = randomGenerator.nextInt(12);
            int row = randomGenerator.nextInt(12);

            if (opponentGrid.positionVessel(new Vessel(vesselType, randomGenerator.nextDouble() < 0.5), col, row)) {
                vesselType--;
            }
        }

        gameActive = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(buildInterface());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}