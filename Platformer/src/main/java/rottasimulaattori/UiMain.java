package rottasimulaattori;

import rottasimulaattori.game.GameLogic;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import rottasimulaattori.ui.Menu;

import java.util.HashMap;

/**
 * Actual main class of the project.
 */
public class UiMain extends Application {

    private GameLogic gameLogic;

    /**
     * Handles the starting of the stage.
     * @param stage
     */
    public void start(Stage stage) {
        stage.setTitle("Rottasimulaattori");
        this.gameLogic = new GameLogic(stage);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
