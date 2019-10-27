package util;

import figures.CurveStickLeft;
import figures.Figure;
import figures.FigureFactory;
import figures.Stick;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import visual.Main;

import static javafx.application.Application.launch;

public class AnimationParts{
    private AnchorPane root;
    Box box = new Box();

    private Scene scene;
    Timeline animation;
     double scale = 1.2;
     int cycleCount = 12;
     double stepScale = 0.1d;
    Figure figure;
    private double rotate = 0.01;

    public void animationDisapere(ObservableList<Node> fakeBoxes) {

        Main.getFakePane().getChildren().addAll(fakeBoxes);

        Timeline animation = null;
        EventHandler<ActionEvent> fall  = event -> {

            scale = scale - stepScale;
            for (Node node : fakeBoxes) {
                Box box = (Box) node;
                box.setScaleX(scale);
                box.setScaleY(scale);
                box.setScaleZ(scale);
            }
        };
        animation = new Timeline(new KeyFrame(Duration.millis(20), fall));
        animation.setCycleCount(cycleCount);
        animation.play();
    }

  /*  @Override
    public void start(Stage primaryStage) throws Exception {
        root = new AnchorPane();
        root.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));

        Figure figure = new CurveStickLeft(root, 5);
        figure.getMoveFigure().stopAnimation();
        figure.setTranslateX(300);
        figure.setTranslateY(300);

        Timeline animation = null;
        EventHandler<ActionEvent> fall  = event -> {
            rotate = rotate + 1.5;
            figure.setRotationAxis(new Point3D(0, 0.01, 0));
            figure.setRotate(rotate);

        };
        animation = new Timeline(new KeyFrame(Duration.millis(20), fall));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        box = new Box(35,35,35);
        box.getTransforms().add(new Rotate(5.9, new Point3D(0.01d, 0.01d, 0)));
        box.setMaterial(new PhongMaterial(Color.BLUE));
        box.translateXProperty().set(300);
        box.translateYProperty().set(300);

        scene = new Scene(root, 600, 680);


        *//*Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);*//*scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.S) {
                //animationDisapere(box);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }*/




}
