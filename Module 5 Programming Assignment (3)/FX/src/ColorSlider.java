import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorSlider extends Application {

    @Override
    public void start(Stage primaryStage) {
        Text sampleText = new Text("Show Colors");
        sampleText.setFont(Font.font(24));
        
        Slider redSlider = createSimpleSlider("Red");
        Slider greenSlider = createSimpleSlider("Green");
        Slider blueSlider = createSimpleSlider("Blue");
        Slider opacitySlider = createSimpleSlider("Opacity");
        opacitySlider.setValue(100);

        redSlider.valueProperty().addListener(_ -> updateColor(sampleText, redSlider, greenSlider, blueSlider, opacitySlider));
        greenSlider.valueProperty().addListener(_ -> updateColor(sampleText, redSlider, greenSlider, blueSlider, opacitySlider));
        blueSlider.valueProperty().addListener(_ -> updateColor(sampleText, redSlider, greenSlider, blueSlider, opacitySlider));
        opacitySlider.valueProperty().addListener(_ -> updateColor(sampleText, redSlider, greenSlider, blueSlider, opacitySlider));
        
        updateColor(sampleText, redSlider, greenSlider, blueSlider, opacitySlider);
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
            sampleText,
            createSliderWithValueLabel(redSlider),
            createSliderWithValueLabel(greenSlider),
            createSliderWithValueLabel(blueSlider),
            createSliderWithValueLabel(opacitySlider)
        );
        
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Color Slider");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Slider createSimpleSlider(String label) {
        Slider slider = new Slider(0, 100, 0);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        slider.setPrefWidth(300);
        slider.setAccessibleText(label);
        return slider;
    }
    
    private VBox createSliderWithValueLabel(Slider slider) {
        Label valueLabel = new Label(String.format("%s: %.0f", 
            slider.getAccessibleText(), 
            slider.getValue()));
            slider.valueProperty().addListener((_, _, newVal) -> {
            valueLabel.setText(String.format("%s: %.0f", slider.getAccessibleText(), newVal.doubleValue()));
        });
        
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(valueLabel, slider);
        return box;
    }
    
    private void updateColor(Text text, Slider red, Slider green, Slider blue, Slider opacity) {
        Color color = Color.rgb(
            (int) red.getValue(),
            (int) green.getValue(),
            (int) blue.getValue(),
            opacity.getValue() / 100.0
        );
        text.setFill(color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}