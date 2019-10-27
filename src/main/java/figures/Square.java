package figures;

import javafx.scene.layout.AnchorPane;

public class Square extends Figure {

    public Square(AnchorPane root, double rateFigures) {
        super(root, rateFigures);
    }

    @Override
    public void figureStructureSetting() {
        double x12 = startPositionX;
        double x34 = (startPositionX  + boxSize * 1) + coeffBetweenBox * 1;
        double y12 = (startPositionY  + boxSize * 2) + coeffBetweenBox * 2;
        double y34 = (startPositionY  + boxSize * 3) + coeffBetweenBox * 3;

        setTranslateYSetTranslateXBox(x12, y12, boxs[0]);
        setTranslateYSetTranslateXBox(x34, y12, boxs[1]);
        setTranslateYSetTranslateXBox(x12, y34, boxs[2]);
        setTranslateYSetTranslateXBox(x34, y34, boxs[3]);
    }

    @Override
    public double[] getNextCoordinateRotation() {
        double[] nextCoordinateArray = new double[8];
        nextCoordinateArray[0] = boxs[0].getTranslateX();
        nextCoordinateArray[1] = boxs[0].getTranslateY();

        nextCoordinateArray[2] = boxs[1].getTranslateX();
        nextCoordinateArray[3] = boxs[1].getTranslateY();

        nextCoordinateArray[4] = boxs[2].getTranslateX();
        nextCoordinateArray[5] = boxs[2].getTranslateY();

        nextCoordinateArray[6] = boxs[3].getTranslateX();
        nextCoordinateArray[7] = boxs[3].getTranslateY();
        return nextCoordinateArray;
    }


}
