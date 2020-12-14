package rottasimulaattori.ui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import rottasimulaattori.data.HiscoresDao;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rottasimulaattori.ui.controls.PlatformerButton;
import rottasimulaattori.ui.controls.PlatformerLabel;

/**
 * This class is the scene that will be displayed when the game is over.
 * It asks for the players name for the hiscores database.
 */
public class EndGameScene {

    private GameLogic gameLogic;
    private Stage stage;

    /**
     * Creates EndGameScene object.
     * Takes in stage and gameLogic as a parameter.
     * It gets required details off the last game from given gamelogic.
     * @param gameLogic
     * @param stage
     */
    public EndGameScene(GameLogic gameLogic, Stage stage) {
        this.gameLogic = gameLogic;
        this.stage = stage;
    }

    /**
     * Returns the endgame scene.
     * @param score
     * @return
     */
    public Scene getEndGameScene(int score) {
        BorderPane borderPane = new BorderPane();
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));
        borderPane.setPrefSize(500, 500);
        PlatformerButton button = new PlatformerButton("Submit");
        TextField textField = new TextField();
        textField.setPromptText("Name:");
        PlatformerLabel label = new PlatformerLabel("Enter name" );
        label.setTextFill(Color.WHITE);
        PlatformerLabel errorLabel = new PlatformerLabel("");
        errorLabel.setTextFill(Color.RED);

        button.setOnMouseClicked((event -> {
            // Blocks empty name and zero score from being saved to database.
            if (textField.getText().isEmpty()) {
                errorLabel.setText("Enter name to continue!");
                return;
            }
            if (score == 0) {
                backToMain();
                return;
            }
            HiscoresDao hiscoresDao = new HiscoresDao("hiscores");
            hiscoresDao.saveScores(new Player(textField.getText(), score));
            backToMain();
        }));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(scoreLabel(score),label,errorLabel,textField, button);
        borderPane.setCenter(vbox);
        Scene scene = new Scene(borderPane);

        return scene;
    }

    /**
     * Shows points in the endgame scene.
     * Adds nice effects to the text.
     * @param score
     * @return
     */
    private HBox scoreLabel(int score){
        HBox hbox = new HBox();
        PlatformerLabel firstPart = new PlatformerLabel("You got ");
        PlatformerLabel scorePart = new PlatformerLabel("" + score);
        PlatformerLabel tukiPart = new PlatformerLabel(" tukikuukautta");
        firstPart.setTextFill(Color.WHITE);
        tukiPart.setTextFill(Color.WHITE);
        scorePart.setStyle("-fx-font-weight: bold");
        scorePart.setTextFill(Color.web("#2ecc71"));
        hbox.getChildren().addAll(firstPart, scorePart , tukiPart);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0,0,50,0));
        return hbox;
    }

    /**
     * Handles transition back to mainmenu and resets the score in the process.
     */
    private void backToMain() {
        this.gameLogic.resetScore();
        stage.setScene(new Menu(stage, this.gameLogic).getMenuScene());
    }

}
