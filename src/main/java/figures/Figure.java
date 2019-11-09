package figures;

import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import movement.MovementFiguresOnField;
import util.Sound;
import visual.Field;

public abstract class Figure extends Group {
    private Sound sound;
    private static int idFigureCount = 0;
    private int idFigure;
    static double startPositionX = Field.getStartX() + 195;
    static double startPositionY = Field.getStartY() - 71;
    static int boxSize = 35;
    static double coeffBetweenBox = 2;

    private MovementFiguresOnField moveFigure;
    int numberPosition = 1;
    Box[] boxs = new Box [4];
    private Figure(){}

    public Figure(AnchorPane root, double rateFigures) {
        super();
        sound = Sound.getInstance();

        idFigure = ++idFigureCount;
        //init boxs
        Color colorBox = getColorRandom();
        for (int i = 0; i < 4; i++) {
            boxs[i] = getPrepareBox(startPositionX, startPositionX, colorBox);
        }
        this.getChildren().addAll(boxs);
        //
        figureStructureSetting();
        //
        moveFigure = new MovementFiguresOnField(this, root, rateFigures);
        //
        root.getChildren().add(this);
    }
    private Color getColorRandom() {
        int r = (int) (Math.random() * 10);
        switch (r) {
            case 0 : return Color.GREEN;
            case 1 : return Color.SLATEGRAY;
            case 2 : return Color.RED;
            case 3 : return Color.DARKORANGE;
            case 4 : return Color.GOLD;
            case 5 : return Color.SEAGREEN;
            case 6 : return Color.CRIMSON;
            case 7 : return Color.YELLOWGREEN;
            case 8 : return Color.BLUEVIOLET;
            case 9 : return Color.MIDNIGHTBLUE;
            default: return Color.KHAKI;
        }
    }
    public final void rotation() {
        sound.shiftOrRotation();
        double x1 = getNextCoordinateRotation()[0];
        double y1 = getNextCoordinateRotation()[1];

        double x2 = getNextCoordinateRotation()[2];
        double y2 = getNextCoordinateRotation()[3];

        double x3 = getNextCoordinateRotation()[4];
        double y3 = getNextCoordinateRotation()[5];

        double x4 = getNextCoordinateRotation()[6];
        double y4 = getNextCoordinateRotation()[7];

            setTranslateYSetTranslateXBox(x1, y1, boxs[0]);
            setTranslateYSetTranslateXBox(x2, y2, boxs[1]);
            setTranslateYSetTranslateXBox(x3, y3, boxs[2]);
            setTranslateYSetTranslateXBox(x4, y4, boxs[3]);

        numberPosition = numberPosition < 4 ? numberPosition+1 :  1;
    }
    void setTranslateYSetTranslateXBox(double x, double y, Box box) {
        box.setTranslateX(x);
        box.setTranslateY(y);
    }
    public Box getPartFigureForIndex(int index) {
        Box res = null;
        ObservableList<Node> remaningPartsFigure = this.getChildren();
        for (Node node : remaningPartsFigure) {
            Box box = (Box) node;
            int indexPart = getPositionIndexPartInArray(box);
            if (indexPart == index) {
                res = box;
                break;
            }
        }
        return res;
    }
    public int getPositionIndexPartInArray(Box part) {
        int rowIndex = (int)(part.getTranslateY() - 54) / 37;
        int columnIndex = (int)part.getTranslateX() / 37;
        return (columnIndex - 1) + (15 - rowIndex) * 10;
    }

    private Box getPrepareBox(double x, double y, Color color) {
        PhongMaterial phongMaterial = new PhongMaterial(color);
        Box box = new Box(boxSize, boxSize, boxSize);
        box.translateXProperty().set(x);
        box.translateYProperty().set(y);
        box.getTransforms().add(new Rotate(5.9, new Point3D(0.01d, 0.01d, 0)));
        box.setMaterial(phongMaterial);
        return box;
    }

    public static int getBoxSize() {
        return boxSize;
    }

    public static void setBoxSize(int boxSize) {
        Figure.boxSize = boxSize;
    }

    public Box[] getBoxs() {
        return boxs;
    }

    @Override
    public String toString() {
        return this.getClass().getName() +" id ="+ idFigure;
    }

    public MovementFiguresOnField getMoveFigure() {
        return moveFigure;
    }

    public abstract double[] getNextCoordinateRotation();

    public abstract void figureStructureSetting();
}
