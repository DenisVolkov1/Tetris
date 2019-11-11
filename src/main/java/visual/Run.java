package visual;

import javafx.application.Application;
import javafx.stage.Stage;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.getInstance(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
