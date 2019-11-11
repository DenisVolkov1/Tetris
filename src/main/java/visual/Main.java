package visual;

import figures.*;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import movement.MovementFiguresOnField;
import util.Sound;

import java.util.List;

public class Main extends  Application {

    private AnchorPane nextFigure;
    private AnchorPane root;
    private AnchorPane figuresPane;
    private static AnchorPane fakePane;
    private MovementFiguresOnField currentMove;
    private Sound sound;

    public static Scene scene;
    public static Label score = new Label();
    public Label scoreWord = new Label();
    public Label nextWord = new Label();

    private Button restartButton;
    private Button soundButton;
    private Line offLineMusic;
    private Line offLineSound;
    private Button musicButton;
    private Figure currentFigure;
    private Field groupField = new Field();

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {
        sound = Sound.getInstance();
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
                        Object objFromList = c.getList().get(index-1);

                        if (!(objFromList instanceof Figure)) return;
                            currentFigure = (Figure) objFromList;
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
        //label
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
        // buttons
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
        int xButtonAllSound = 475;
        offLineMusic = new Line();
        offLineMusic.setStartX(xButtonAllSound);
        offLineMusic.setStartY(40);
        offLineMusic.setEndX(xButtonAllSound+25);
        offLineMusic.setEndY(5);
        offLineMusic.setStrokeWidth(5);
        offLineMusic.setStroke(Color.RED);

        musicButton = new Button("M");
        musicButton.setFocusTraversable(false);
        musicButton.setPrefHeight(35);
        musicButton.setPrefWidth(30);
        musicButton.setTranslateX(xButtonAllSound);
        musicButton.setTranslateY(5);
        musicButton.setStyle(""+
                "-fx-font: 20px Helvetica;"+
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;"+
                "-fx-padding : 0 0 0 0;"
        );
        offLineSound = new Line();
        offLineSound.setStartX(xButtonAllSound+40);
        offLineSound.setStartY(40);
        offLineSound.setEndX(xButtonAllSound+65);
        offLineSound.setEndY(5);
        offLineSound.setStrokeWidth(5);
        offLineSound.setStroke(Color.RED);

        soundButton = new Button("S");
        soundButton.setFocusTraversable(false);
        soundButton.setPrefHeight(35);
        soundButton.setPrefWidth(30);
        soundButton.setTranslateX(xButtonAllSound+40);
        soundButton.setTranslateY(5);
        soundButton.setStyle("" +
                "-fx-font: 20px Helvetica;"+
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;"+
                "-fx-padding : 0 0 0 0;"
        );
        offLineMusic.setVisible(false);
        offLineSound.setVisible(false);
        //
        root.getChildren().add(nextFigure);
        root.getChildren().add(nextWord);
        root.getChildren().add(restartButton);
        root.getChildren().add(musicButton);
        root.getChildren().add(soundButton);
        root.getChildren().add(offLineMusic);
        root.getChildren().add(offLineSound);
        root.getChildren().add(scoreWord);
        root.getChildren().add(score);
        // buttons handlers
        restartButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            sound.buttonHover();
        });
        musicButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            sound.buttonHover();
        });
        soundButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            sound.buttonHover();
        });
        musicButton.setOnMousePressed(event -> {
            sound.buttonClick();
            if (offLineMusic.isVisible()) {
                offLineMusic.setVisible(false);
            } else {
                offLineMusic.setVisible(true);
            }
            sound.musicSwitch();
        });
        soundButton.setOnMousePressed(event -> {
            sound.buttonClick();
            if (offLineSound.isVisible()) {
                offLineSound.setVisible(false);
            } else {
                offLineSound.setVisible(true);
            }
            sound.soundSwitch();
        });

        restartButton.setOnMousePressed(event -> {
            sound.buttonClick();
            figuresPane.getChildren().clear();
            currentMove.stopAnimation();
            figuresPane.getChildren().clear();
            MovementFiguresOnField.setLocationParts(new boolean[160]);
            MovementFiguresOnField.setScore(0);
            score.setText("0");
            FigureFactory.setRateFigures(8.0);
            FigureFactory.getFiguresIndex().clear();

            FigureFactory.createInstanceAndAddFigureInList(figuresPane);
        });


       /* Camera camera = new PerspectiveCamera();
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
                currentMove.pause();
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
