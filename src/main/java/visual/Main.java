package visual;

import figures.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import movement.MovementFiguresOnField;

import java.util.List;

public class Main extends  Application {
    private AnchorPane nextFigure;
    private AnchorPane root;
    private AnchorPane figuresPane;
    private static AnchorPane fakePane;
    private MovementFiguresOnField currentMove;

    private Scene scene;
    public static Label score = new Label();
    public Label scoreWord = new Label();
    public Label nextWord = new Label();
    private Button restartButton;
    private Figure currentFigure;
    private Field groupField = new Field();
    private double rotate;


    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {

        figuresPane = new AnchorPane();
        fakePane = new AnchorPane();
        nextFigure = new AnchorPane();
        root = new AnchorPane(groupField);
        root.getChildren().add(figuresPane);
        root.getChildren().add(fakePane);

        root.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));

        nextFigure.setLayoutX(300);
        nextFigure.setLayoutY(430);

        figuresPane.getChildren().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                if(c.next()) {
                    if (c.wasAdded()) {
                        List list = c.getList();
                        int index = list.size();
                        currentFigure = (Figure) c.getList().get(index-1);
                        currentMove = currentFigure.getMoveFigure();
                        System.out.println("Current figure: "+currentFigure);

                        nextFigure.getChildren().clear();
                        nextFigure();

                    }
                }
            }
        });
        FigureFactory.createInstanceAndAddFigureInList(figuresPane);
        currentMove = currentFigure.getMoveFigure();

        primaryStage.setTitle("Tetris");
        scene = new Scene(root, 600, 680);

        scoreWord.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
        scoreWord.setTranslateX(465);
        scoreWord.setTranslateY(60);
        scoreWord.setText("Score:");

        nextWord.setFont(Font.font("Helvetica", FontWeight.BOLD,28));
        nextWord.setTranslateX(465);
        nextWord.setTranslateY(305);
        nextWord.setText("Next:");

        score.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
        score.setTranslateX(495);
        score.setTranslateY(100);
        score.setText("0");
        //
        restartButton = new Button("Restart");
        restartButton.setFocusTraversable(false);
        restartButton.setPrefHeight(45);
        restartButton.setPrefWidth(150);
        restartButton.setTranslateX(430);
        restartButton.setTranslateY(180);
        restartButton.setStyle("" +
                "-fx-font: 30px Helvetica;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7;"
                 );

        restartButton.setOnMousePressed(event -> {
            figuresPane.getChildren().clear();
            currentMove.stopAnimation();
            figuresPane.getChildren().clear();
            MovementFiguresOnField.setLocationParts(new boolean[160]);
            MovementFiguresOnField.setScore(0);
            score.setText("0");
            FigureFactory.setRateFigures(8.0);

            FigureFactory.createInstanceAndAddFigureInList(figuresPane);

        });

        root.getChildren().add(nextFigure);
        root.getChildren().add(nextWord);
        root.getChildren().add(restartButton);
        root.getChildren().add(scoreWord);
        root.getChildren().add(score);

        /*Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);*/

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                currentMove.movementLeft();
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                currentMove.movementRight();
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                currentMove.rotation();
            }
            if (keyEvent.getCode() == KeyCode.P) {
                currentMove.stopFall();
            }
            if (keyEvent.getCode() == KeyCode.S) {
                currentMove.movementFall();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static AnchorPane getFakePane() {
        return fakePane;
    }
    private void nextFigure() {
        Figure figure = null;
        switch (FigureFactory.getFiguresIndex().get(0)) {
            case 0:
                figure = new Stick(nextFigure, 0);
                break;
            case 1:
                figure = new CurveStickRight(nextFigure, 0);
                break;
            case 2:
                figure = new TShaped(nextFigure, 0);
                break;
            case 3:
                figure = new Square(nextFigure, 0);
                break;
            case 4:
                figure = new CurveStickLeft(nextFigure, 0);
                break;
            case 5:
                figure = new ChairLeft(nextFigure, 0);
                break;
            case 6:
                figure = new ChairRight(nextFigure, 0);
                break;
        }
        for (Box box :figure.getBoxs()) {
            box.getTransforms().add(new Rotate(0, new Point3D(0.01d, 0.01d, 0)));
            box.setHeight(40);
            box.setWidth(40);
            box.setDepth(36);
            PhongMaterial phongMaterial = new PhongMaterial(Color.rgb(105, 105, 105));
            box.setMaterial(phongMaterial);
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
