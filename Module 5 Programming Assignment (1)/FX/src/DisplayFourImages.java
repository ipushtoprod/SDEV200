    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.GridPane;
    import javafx.stage.Stage;

    public class DisplayFourImages extends Application {

        @Override
        public void start(Stage primaryStage) {
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            try {
                Image image1 = new Image("Images/flag2.gif");
                Image image2 = new Image("Images/flag7.gif");
                Image image3 = new Image("Images/flag6.gif");
                Image image4 = new Image("Images/flag1.gif");
                
                ImageView imageView1 = new ImageView(image1);
                ImageView imageView2 = new ImageView(image2);
                ImageView imageView3 = new ImageView(image3);
                ImageView imageView4 = new ImageView(image4);
                
                imageView1.setFitWidth(200);
                imageView1.setFitHeight(150);
                imageView1.setPreserveRatio(true);
                
                imageView2.setFitWidth(200);
                imageView2.setFitHeight(150);
                imageView2.setPreserveRatio(true);
                
                imageView3.setFitWidth(200);
                imageView3.setFitHeight(150);
                imageView3.setPreserveRatio(true);
                
                imageView4.setFitWidth(200);
                imageView4.setFitHeight(150);
                imageView4.setPreserveRatio(true);

                gridPane.add(imageView1, 0, 0);
                gridPane.add(imageView2, 1, 0);
                gridPane.add(imageView3, 0, 1);
                gridPane.add(imageView4, 1, 1);
                
            } catch (Exception e) {
                System.out.println("Error loading images: " + e.getMessage());
                e.printStackTrace();
            }

            Scene scene = new Scene(gridPane);
            primaryStage.setTitle("Four Images in Grid Pane");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }