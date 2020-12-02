package rottasimulaattori.ui;

import rottasimulaattori.game.GameLogic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rottasimulaattori.ui.controls.PlatformerButton;
import rottasimulaattori.ui.controls.PlatformerLabel;

public class GameScene {

    private GameLogic gameLogic;

    public GameScene(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public Scene getGameScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(500, 500);
        PlatformerButton button = new PlatformerButton("Press");
        PlatformerButton endGame = new PlatformerButton("End");
        PlatformerLabel label = new PlatformerLabel("" + this.gameLogic.getScore());


        button.setOnMouseClicked((event -> {
            this.gameLogic.addScore();
            label.setText("" + this.gameLogic.getScore());
        }));

        endGame.setOnMouseClicked((event -> {
            EndGameScene endGameScene = new EndGameScene(gameLogic, gameLogic.getStage());
            this.gameLogic.changeScene(endGameScene.getEndGameScene(this.gameLogic.getScore()));
        }));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, button, endGame);
        borderPane.setCenter(vbox);
        Scene scene = new Scene(borderPane);

        return scene;
    }


}
