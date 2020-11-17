import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import menu.Menu;

import java.util.HashMap;

public class UiMain extends Application {

    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

    public void start(Stage stage) {

        stage.setTitle("Rottasimulaattori");

        stage.setScene(new Menu(stage).getMenuScene());


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
