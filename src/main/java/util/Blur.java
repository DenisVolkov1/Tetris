package util;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import visual.Main;

public class Blur {

    public static void blurEffectOnBackground(AnchorPane root) {
        Effect frostEffect =
                new BoxBlur(10, 10, 3);
        ImageView background = new ImageView();
        WritableImage image = Main.scene.snapshot( null);
        background.setImage(image);
        background.setEffect(frostEffect);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().setAll(background);
        stackPane.setStyle("-fx-background-color: null");
        root.getChildren().add(stackPane);
    }
}
