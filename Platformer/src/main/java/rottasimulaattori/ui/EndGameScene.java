package rottasimulaattori.ui;

import rottasimulaattori.data.HiscoresDao;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rottasimulaattori.ui.controls.PlatformerButton;
import rottasimulaattori.ui.controls.PlatformerLabel;

public class EndGameScene {

    private GameLogic gameLogic;
    private Stage stage;

    public EndGameScene(GameLogic gameLogic, Stage stage) {
        this.gameLogic = gameLogic;
        this.stage = stage;
    }


    public Scene getEndGameScene(int score) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(500, 500);
        PlatformerButton button = new PlatformerButton("Submit");
        TextField textField = new TextField();
        textField.setPromptText("Name:");
        PlatformerLabel label = new PlatformerLabel("Enter name" );

        button.setOnMouseClicked((event -> {
            // Blocks empty name and zero score from being saved to database.
            if (textField.getText().isEmpty() || score == 0) {
                backToMain();
                return;
            }
            HiscoresDao hiscoresDao = new HiscoresDao("hiscores");
            hiscoresDao.saveScores(new Player(textField.getText(), score));

            System.out.println(textField.getText() + " Score: " + score);
            backToMain();
        }));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label,textField, button);
        borderPane.setCenter(vbox);
        Scene scene = new Scene(borderPane);


        //Reset gamescore after ending rottasimulaattori.game.


        return scene;
    }

    private void backToMain() {
        this.gameLogic.resetScore();
        stage.setScene(new Menu(stage, this.gameLogic).getMenuScene());
    }

}
