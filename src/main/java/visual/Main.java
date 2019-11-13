package visual;

import figures.*;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import movement.MovementFiguresOnField;
import util.Blur;
import util.Sound;

import java.util.List;

public class Main {

    private static Stage primaryStage;
    private static Main main;
    private static AnchorPane fakePane;
    private AnchorPane nextFigure;
    private AnchorPane root;
    private AnchorPane figuresPane;
    private MovementFiguresOnField currentMove;
    private Sound sound;

    public static Scene scene;
    public  Label score = new Label();
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
    private Main() {
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
        // first figure start game
        FigureFactory.createInstanceAndAddFigureInList(figuresPane);
        currentMove = currentFigure.getMoveFigure();
        //settings Main
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
    public static Main getInstance(Stage pStage) {
        primaryStage = pStage;
        if (main == null) {
            main = new Main();
            return main;
        } else {
            return main;
        }
    }
    public void blurGameOver() {
        sound.loseGame();
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(200);
        rectangle.setLayoutX(120);
        rectangle.setLayoutY(310);
        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.75);

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setHeight(60);
        rectangle2.setWidth(210);
        rectangle2.setLayoutX(115);
        rectangle2.setLayoutY(305);
        rectangle2.setFill(Color.RED);
        rectangle2.setOpacity(0.5);
        Label label = new Label("Game Over");
        label.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
        label.setLayoutX(143);
        label.setLayoutY(315);

        Blur.blurEffectOnBackground(figuresPane);
        figuresPane.getChildren().add(rectangle);
        figuresPane.getChildren().add(rectangle2);
        figuresPane.getChildren().add(label);

    }
    private boolean pause = false;
    public void blurPause() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(50);
        rectangle.setWidth(150);
        rectangle.setLayoutX(140);
        rectangle.setLayoutY(310);
        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.7);
            Label label = new Label("Pause");
            label.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
            label.setLayoutX(175);
            label.setLayoutY(315);

            if (!pause) {
                Blur.blurEffectOnBackground(root);
                root.getChildren().add(rectangle);
                root.getChildren().add(label);
            } else {
                for (int i = 1; i <= 3; i++) {
                    root.getChildren().remove(root.getChildren().size()-1);
                }
            }
        pause = !pause;

    }
    public static Main getInstance() {
        if (main == null && primaryStage == null) {
            throw new AbsenceOfStageException("Install Stage use Main.getInstance(Stage pStage)");
        } else {
            return main;
        }
    }

}
