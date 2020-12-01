import game.GameLogic;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ui.Menu;

import java.util.HashMap;

public class UiMain extends Application {

    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private GameLogic gameLogic;

    public void start(Stage stage) {

        stage.setTitle("Rottasimulaattori");
        this.gameLogic = new GameLogic(stage);
        stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
