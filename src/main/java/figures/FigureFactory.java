package figures;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FigureFactory {

    private static double rateFigures = 8.0;
    private static List<Integer> figuresIndex = new ArrayList<>();

    private static int getRandomIndex() {
        int res = 0;

        if (figuresIndex.size() == 1) {
            res = figuresIndex.remove(0);
            for (int i = 0; i <= 6; i++) {
                figuresIndex.add(i);
            }
            Collections.shuffle(figuresIndex);
        } else if (figuresIndex.size() == 0) {
            for (int i = 0; i <= 6; i++) {
                figuresIndex.add(i);
            }
            Collections.shuffle(figuresIndex);
            res = figuresIndex.remove(0);
        } else {
            res = figuresIndex.remove(0);
        }
        return res;
    }
    public static Figure createInstanceAndAddFigureInList(AnchorPane root) {
        Figure figure = null;
        switch (getRandomIndex()) {
            case 0:
                figure = new Stick(root, rateFigures);
                break;
            case 1:
                figure = new CurveStickRight(root, rateFigures);
                break;
            case 2:
                figure = new TShaped(root, rateFigures);
                break;
            case 3:
                figure = new Square(root, rateFigures);
                break;
            case 4:
                figure = new CurveStickLeft(root, rateFigures);
                break;
            case 5:
                figure = new ChairLeft(root, rateFigures);
                break;
            case 6:
                figure = new ChairRight(root, rateFigures);
                break;
        }
        return figure;
    }

    public static double getRateFigures() {
        return rateFigures;
    }

    public static void setRateFigures(double rateFigures) {
        FigureFactory.rateFigures = rateFigures;
    }

    public static List<Integer> getFiguresIndex() {
        return figuresIndex;
    }
}
