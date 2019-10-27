package movement;

import figures.Figure;
import figures.FigureFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import javafx.util.Duration;
import util.AnimationParts;
import visual.Main;

public class MovementFiguresOnField {

    private static boolean[] locationParts = new boolean[160];
    private static boolean[] deleteRows = new boolean[16];

    private Timeline animationFall;


    private AnchorPane root;
    private static int score;

    private final double  leftBoundFild = 49.0;
    private final double  rightBoundFild = 382.0;
    private final double  downBoundFild = 644.0;


    private Figure figure;

    public MovementFiguresOnField(Figure figure, AnchorPane root, double rateFigures) {

        this.figure = figure;
        this.root = root;

        EventHandler<ActionEvent> fall  = event -> {
            moveFall();
        };
        animationFall = new Timeline(1000d,new KeyFrame(Duration.millis(rateFigures), fall));
        animationFall.setCycleCount(Animation.INDEFINITE);
        animationFall.play();
    }

    private int start = 0;
    private int end = 9;
    private void checkOnRemovesRows() {
        deleteRows = new boolean[16];
        for (int i = 0; i < 15; i++) {

            boolean[] b = new boolean [10];
            while (start <= end) {
                b[end-start] = locationParts[start];
                ++start;
            }
            end +=10;

            if (isRowRemove(b)) {
                System.out.println("remove row - " + i);
                score = score + 8;
                Main.score.setText(Integer.toString(score));
                setRateFigure();

                deleteRows[i] = true;
            }
        }

        for (int i = 15; i >= 0; i--) {
            if (deleteRows[i]) columnsDown(i);
        }
    }
    private void setRateFigure() {
        if (score == 8)  {FigureFactory.setRateFigures(8.0); return;}
        if (score == 24) {FigureFactory.setRateFigures(7.5); return;}
        if (score == 32) {FigureFactory.setRateFigures(7.0); return;}
        if (score == 64) {FigureFactory.setRateFigures(6.5); return;}
        if (score == 72) {FigureFactory.setRateFigures(6.0); return;}
        if (score == 120) {FigureFactory.setRateFigures(5.5); return;}
        if (score == 160) {FigureFactory.setRateFigures(5.5); return;}
        if (score == 200) {FigureFactory.setRateFigures(5.0); return;}
        if (score == 280) {FigureFactory.setRateFigures(4.0); return;}
    }

    private void columnsDown(int countRow) {
        int ind = countRow*10;
        for (int i = ind; i < 150; i++) {
            locationParts[i] = locationParts[i+10];
        }
    }
    private boolean isRowRemove(boolean[] row) {
        for (boolean b : row) {
            if (!b) return false;
        }
        return true;
    }
    public void stopFall() {
        animationFall.pause();
    }

    public void movementFall() {
        animationFall.play();
    }

    public void movementLeft() {
       moveLeft();
    }
    public void movementRight() {
        moveRight();
    }

    private boolean checkConstraintFall(Box[] parts) {
        for (int i = 0; i < 4; i++) {
            int rowIndex = (int) (parts[i].getTranslateY() - 54 + 2) / 37;
            int columnIndex = (int) parts[i].getTranslateX() / 37;
            int indexCheckPossibleLocation = (columnIndex - 1) + (15 - rowIndex) * 10;
            if (indexCheckPossibleLocation > 160 || indexCheckPossibleLocation < 0) return true;
            if (locationParts[indexCheckPossibleLocation]) return false;
        }
        return true;
    }
    private boolean checkConstraintLeftMove(Box[] parts) {
        for (int i = 0; i < 4;i++) {
            int rowIndex = (int)(parts[i].getTranslateY() - 54) / 37;
            int columnIndex = ((int)parts[i].getTranslateX() -37) / 37;
            int indexCheckPossibleLocation =  (columnIndex - 1) + (15 - rowIndex) * 10;
            if (indexCheckPossibleLocation > 160 || indexCheckPossibleLocation < 0) return false;
            if (locationParts[indexCheckPossibleLocation]) return false;
        }
        return true;
    }
    private boolean checkConstraintRightMove(Box[] parts) {

        for (int i = 0; i < 4;i++) {
            int rowIndex = (int)(parts[i].getTranslateY() - 54) / 37;
            int columnIndex = ((int)parts[i].getTranslateX() +37) / 37;
            int indexCheckPossibleLocation =  (columnIndex - 1) + (15 - rowIndex) * 10;
            if (indexCheckPossibleLocation > 160 || indexCheckPossibleLocation < 0) return false;
            if (locationParts[indexCheckPossibleLocation]) return false;
        }
        return true;
    }
    private void commitFigureInArray() {
        int indexPart1 = figure.getPositionIndexPartInArray(figure.getBoxs()[0]);
        int indexPart2 = figure.getPositionIndexPartInArray(figure.getBoxs()[1]);
        int indexPart3 = figure.getPositionIndexPartInArray(figure.getBoxs()[2]);
        int indexPart4 = figure.getPositionIndexPartInArray(figure.getBoxs()[3]);
        locationParts[indexPart1] = true;
        locationParts[indexPart2] = true;
        locationParts[indexPart3] = true;
        locationParts[indexPart4] = true;
    }
    private void disapereRow(ObservableList<Node> figures, int row) {
        ObservableList<Node> fakeBoxes = FXCollections.observableArrayList();

        for (int i = row*10;i < (row*10)+10; i++) {
            for (Node figure : figures) {
                Figure f = (Figure) figure;
                Box box = f.getPartFigureForIndex(i);
                if(box != null) {
                    fakeBoxes.add(box);
                    f.getChildren().remove(box);
                    break;
                }
            }
        }
        new AnimationParts().animationDisapere(fakeBoxes);

        ObservableList<Node> emptyFigures = FXCollections.observableArrayList();
        for (Node figure : figures) {
            Figure f = (Figure) figure;
            if (f.getChildren().size() == 0) emptyFigures.add(f);
        }
        root.getChildren().removeAll(emptyFigures);
    }


    private void delete(ObservableList<Node> figures, ObservableList<Node> q) {
        for (Node figure : figures) {
            Figure f = (Figure) figure;
            for (Node box : q) {
                f.getChildren().remove(box);
            }
        }
    }

    private void downAllFiguresUpperRemoveLine(ObservableList<Node> figures, int row) {
        for (int i = row*10+10; i < 160; i++) {
            for (Node figure : figures) {
                Figure f = (Figure) figure;
                Box box = f.getPartFigureForIndex(i);
                if(box != null) {
                    box.setTranslateY(box.getTranslateY()+37);
                    break;
                }
            }
        }

    }
    public void stopAnimation() {
        animationFall.stop();
        animationFall.getKeyFrames().clear();
    }

    private void setTranslateXBoxes(double...argX) {
        for (int i = 0; i < 4; i++) {
            figure.getBoxs()[i].setTranslateX(argX[i]);
        }
    }
    private void setTranslateYBoxes(double...argY) {
        for (int i = 0; i < 4; i++) {
            figure.getBoxs()[i].setTranslateY(argY[i]);
        }
    }
    private double[] getTranslateYBoxes() {
        double[] d = new double[4];
        for (int i = 0; i < 4; i++) {
            d[i] = figure.getBoxs()[i].getTranslateY();
        }
        return d;
    }
    private double[] getTranslateXBoxes() {
        double[] d = new double[4];
        for (int i = 0; i < 4; i++) {
            d[i] = figure.getBoxs()[i].getTranslateX();
        }
        return d;
    }
    double[] currentY;
    private boolean borderCheckOnDown() {
        currentY = this.getTranslateYBoxes();
        for (int i = 0; i < 4; i++) {
            if (currentY[i] + 1 > downBoundFild) return false;
        }
        return true;
    }
    private double[] currentX;
    private boolean borderCheckOnRight() {
        currentX = this.getTranslateXBoxes();
        for (int i = 0; i < 4; i++) {
            if (currentX[i] + 37.0 > rightBoundFild) return false;
        }
        return true;
    }
    private boolean borderCheckOnLeft() {
        currentX = this.getTranslateXBoxes();
        for (int i = 0; i < 4; i++) {
            if (currentX[i] - 37.0 < leftBoundFild) return false;
        }
        return true;
    }
    private void moveRight() {
        boolean checkConstraint = checkConstraintRightMove(figure.getBoxs());
        boolean borderCheck = borderCheckOnRight();
        if (borderCheck && checkConstraint) {
            for (int i = 0; i < 4; i++) currentX[i] += 37.0;
        }
        this.setTranslateXBoxes(currentX);
    }
    private void moveLeft() {
        boolean checkConstraint = checkConstraintLeftMove(figure.getBoxs());
        boolean borderCheck = borderCheckOnLeft();
        if (borderCheck && checkConstraint) {
            for (int i = 0; i < 4; i++) currentX[i] -= 37.0;
        }
        this.setTranslateXBoxes(currentX);
    }
    private void moveFall() {
        boolean checkConstraint = checkConstraintFall(figure.getBoxs());
        boolean borderCheck = borderCheckOnDown();
        if (borderCheck && checkConstraint) {
            for (int i = 0; i < 4; i++) currentY[i] += 1;
            this.setTranslateYBoxes(currentY);
        } else {
            stopAnimation();
            commitFigureInArray();
            checkOnRemovesRows();
            for (int j = 15; j >= 0; j--) {
                if (deleteRows[j]) {

                    disapereRow(root.getChildren(), j);
                    downAllFiguresUpperRemoveLine(root.getChildren(), j);
                }
            }
            FigureFactory.createInstanceAndAddFigureInList(root);
        }
    }
    public void rotation() {
        double[] coordinateBoxes = figure.getNextCoordinateRotation();
        for (int i = 0; i < 8; i = i+2) {
            if (coordinateBoxes[i]  < leftBoundFild) return;
            if (coordinateBoxes[i]  > rightBoundFild) return;
            if (coordinateBoxes[i+1]  > downBoundFild) return;

            int rowIndex = (int)(coordinateBoxes[i+1] - 54) / 37;
            int columnIndex = (int)coordinateBoxes[i] / 37;
            int indexCheckPossibleLocation =  (columnIndex - 1) + (15 - rowIndex) * 10;
            if (indexCheckPossibleLocation > 160 || indexCheckPossibleLocation < 0) return;
            if (locationParts[indexCheckPossibleLocation]) return;
        }
        figure.rotation();
    }
    public static void setLocationParts(boolean[] locationParts) {
        MovementFiguresOnField.locationParts = locationParts;
    }

    public static void setScore(int score) {
        MovementFiguresOnField.score = score;
    }
}
