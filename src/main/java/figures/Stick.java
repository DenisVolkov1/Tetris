package figures;

import javafx.scene.layout.AnchorPane;

public class Stick extends Figure {
    public Stick(AnchorPane root, double rateFigures) {
        super(root, rateFigures);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void figureStructureSetting() {
        double y1 = (startPositionY + boxSize * 0) + coeffBetweenBox*0;
        double y2 = (startPositionY + boxSize * 1) + coeffBetweenBox*1;
        double y3 = (startPositionY + boxSize * 2) + coeffBetweenBox*2;
        double y4 = (startPositionY + boxSize * 3) + coeffBetweenBox*3;
        double x = startPositionX;

        setTranslateYSetTranslateXBox(x, y1, boxs[0]);
        setTranslateYSetTranslateXBox(x, y2, boxs[1]);
        setTranslateYSetTranslateXBox(x, y3, boxs[2]);
        setTranslateYSetTranslateXBox(x, y4, boxs[3]);
    }

    @Override
    public double[] getNextCoordinateRotation() {
        double[] nextCoordinateArray = new double[8];

        switch(numberPosition) {
            case 1:
            {
                nextCoordinateArray[0] = boxs[0].getTranslateX()+2*37;
                nextCoordinateArray[1] = boxs[0].getTranslateY()+2*37;

                nextCoordinateArray[2] = boxs[1].getTranslateX()+37;
                nextCoordinateArray[3] = boxs[1].getTranslateY()+37;

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY();

                nextCoordinateArray[6] = boxs[3].getTranslateX()-37;
                nextCoordinateArray[7] = boxs[3].getTranslateY()-37;
            }
            break;

            case 2:

            {
                nextCoordinateArray[0] = boxs[0].getTranslateX()-2*37;
                nextCoordinateArray[1] = boxs[0].getTranslateY()+2*37;

                nextCoordinateArray[2] = boxs[1].getTranslateX()-37;
                nextCoordinateArray[3] = boxs[1].getTranslateY()+37;

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY();

                nextCoordinateArray[6] = boxs[3].getTranslateX()+37;
                nextCoordinateArray[7] = boxs[3].getTranslateY()-37;
            }
            break;

            case 3:
            {
                nextCoordinateArray[0] = boxs[0].getTranslateX()-2*37;
                nextCoordinateArray[1] = boxs[0].getTranslateY()-2*37;

                nextCoordinateArray[2] = boxs[1].getTranslateX()-37;
                nextCoordinateArray[3] = boxs[1].getTranslateY()-37;

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY();

                nextCoordinateArray[6] = boxs[3].getTranslateX()+37;
                nextCoordinateArray[7] = boxs[3].getTranslateY()+37;

            }
            break;

            case 4:
            {
                nextCoordinateArray[0] = boxs[0].getTranslateX()+2*37;
                nextCoordinateArray[1] = boxs[0].getTranslateY()-2*37;

                nextCoordinateArray[2] = boxs[1].getTranslateX()+37;
                nextCoordinateArray[3] = boxs[1].getTranslateY()-37;

                nextCoordinateArray[4] = boxs[2].getTranslateX();
                nextCoordinateArray[5] = boxs[2].getTranslateY();

                nextCoordinateArray[6] = boxs[3].getTranslateX()-37;
                nextCoordinateArray[7] = boxs[3].getTranslateY()+37;
            }
            break;
        }
        return nextCoordinateArray;
    }
}
