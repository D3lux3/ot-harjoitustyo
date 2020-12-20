package rottasimulaattori.game;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.annotation.processing.Generated;

/**
 * This class keeps track of the current game score.
 * And handling scene changes.
 */
public class GameLogic {

    private int score;
    private Stage stage;

    /**
     * Creates GameLogic object.
     * Takes stage as a parameter for future scene change purposes.
     * @param stage Stage
     */
    public GameLogic(Stage stage) {
        this.score = 0;
        this.stage = stage;
    }
    /**
     * Creates GameLogic object.
     * This constructor is for testing purposes only.
     */
    public GameLogic() {
        this.score = 0;
    }

    /**
     * Adds score by one.
     */
    public void addScore() {
        this.score++;
    }

    /**
     * Adds score by given amount.
     * @param score Adds score by given amount.
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Returns score.
     * @return Returns score as int
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Resets current games score.
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Returns Stage object.
     * @return Returns stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Handles scene change.
     * @param scene Scene
     */
    public void changeScene(Scene scene) {
        this.stage.setScene(scene);
    }

}
