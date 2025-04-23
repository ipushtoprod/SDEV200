import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class BlackWhiteCircle extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, Color.WHITE);
        circle.setStroke(Color.BLACK);

        StackPane root = new StackPane(circle);

        circle.setOnMousePressed(_ -> circle.setFill(Color.BLACK));
        circle.setOnMouseReleased(_ -> circle.setFill(Color.WHITE));
        
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Black/White Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}