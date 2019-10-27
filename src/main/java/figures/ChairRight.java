package figures;

import javafx.scene.layout.AnchorPane;

public class ChairRight extends Figure {
    public ChairRight(AnchorPane root, double rateFigures) {
        super(root, rateFigures);
    }
    @Override
    public void figureStructureSetting() {
        double y1 = (startPositionY + boxSize * 1) + coeffBetweenBox * 1;
        double x1 = startPositionX;

        double y2 = (startPositionY + boxSize * 2) + coeffBetweenBox * 2;
        double x2 = startPositionX;

        double y3 = (startPositionY + boxSize * 2) + coeffBetweenBox * 2;
        double x3 =  (startPositionX + boxSize * 1) + coeffBetweenBox * 1;

        double y4 = (startPositionY + boxSize * 3) + coeffBetweenBox * 3;
        double x4 = (startPositionX + boxSize * 1) + coeffBetweenBox * 1;


        setTranslateYSetTranslateXBox(x1, y1, boxs[0]);
        setTranslateYSetTranslateXBox(x2, y2, boxs[1]);
        setTranslateYSetTranslateXBox(x3, y3, boxs[2]);
        setTranslateYSetTranslateXBox(x4, y4, boxs[3]);
    }

    @Override
    public double[] getNextCoordinateRotation() {
        double[] nextCoordinateArray = new double[8];

        switch (numberPosition) {
            case 1:

            {
                nextCoordinateArray[0] = boxs[0].getTranslateX() + 2 * 37;
                nextCoordinateArray[1] = boxs[0].getTranslateY() + 1 * 37;

                nextCoordinateArray[2] = boxs[1].getTranslateX() + 1 * 37;
                nextCoordinateArray[3] = boxs[1].getTranslateY();

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY() + 1 * 37;

                nextCoordinateArray[6] = boxs[3].getTranslateX() - 1 * 37;
                nextCoordinateArray[7] = boxs[3].getTranslateY();
            }
            break;
            case 2:

            {
                nextCoordinateArray[0] = boxs[0].getTranslateX() - 2 * 37;
                nextCoordinateArray[1] = boxs[0].getTranslateY() - 1 * 37;

                nextCoordinateArray[2] = boxs[1].getTranslateX() - 1 * 37;
                nextCoordinateArray[3] = boxs[1].getTranslateY();

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY() - 1 * 37;

                nextCoordinateArray[6] = boxs[3].getTranslateX() + 1 * 37;
                nextCoordinateArray[7] = boxs[3].getTranslateY();
            }
            break;
            case 3:

            {
                nextCoordinateArray[0] = boxs[0].getTranslateX() + 2 * 37;
                nextCoordinateArray[1] = boxs[0].getTranslateY() + 1 * 37;

                nextCoordinateArray[2] = boxs[1].getTranslateX() + 1 * 37;
                nextCoordinateArray[3] = boxs[1].getTranslateY();

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY() + 1 * 37;

                nextCoordinateArray[6] = boxs[3].getTranslateX() - 1 * 37;
                nextCoordinateArray[7] = boxs[3].getTranslateY();

            }
            break;
            case 4:

            {
                nextCoordinateArray[0] = boxs[0].getTranslateX() - 2 * 37;
                nextCoordinateArray[1] = boxs[0].getTranslateY() - 1 * 37;

                nextCoordinateArray[2] = boxs[1].getTranslateX() - 1 * 37;
                nextCoordinateArray[3] = boxs[1].getTranslateY();

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY() - 1 * 37;

                nextCoordinateArray[6] = boxs[3].getTranslateX() + 1 * 37;
                nextCoordinateArray[7] = boxs[3].getTranslateY();
            }
            break;
        }
        return nextCoordinateArray;
    }


}
