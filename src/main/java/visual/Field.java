package visual;

import figures.Figure;
import javafx.geometry.Bounds;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends Pane {

    private Rectangle[][] rects = new Rectangle [16][10];
    private int boxSize = Figure.getBoxSize();
    private double fieldBoxSize = boxSize * 1;
    private static int startX = 2;
    private static int startY = 12;
    private static double distanceBetweenCells = 2;

    private int rectsX = startX;
    private int rectsY = startY;

    public Field() {

        setPrefHeight(604);
        setPrefWidth(372);
            setLayoutX(30);
            setLayoutY(60);

        for (Rectangle[] rectangles : rects) {
            for (Rectangle rectangle : rectangles) {
                rectangle = new Rectangle(rectsX, rectsY, fieldBoxSize, fieldBoxSize);
                rectangle.setOpacity(0.7);
                rectsX += fieldBoxSize+distanceBetweenCells;
                rectangle.setFill(Color.WHITE);
                this.getChildren().add(rectangle);
            }
            rectsX = 2;
            rectsY += fieldBoxSize+distanceBetweenCells;
        }
    }


    public static int getStartX() {
        return startX;
    }

    public static int getStartY() {
        return startY;
    }



}
