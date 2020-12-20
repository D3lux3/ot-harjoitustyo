package rottasimulaattori.ui;

import rottasimulaattori.data.HiscoresDao;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rottasimulaattori.ui.controls.PlatformerButton;
import rottasimulaattori.ui.controls.PlatformerLabel;

import java.util.ArrayList;

/**
 * Hiscores scene for the GUI.
 */
public class Hiscores {

    private final String BG_PATH = "yellow_panel.png";
    private Stage stage;
    private GameLogic gameLogic;

    /**
     * Creates Hiscores object.
     * @param stage Stage
     * @param gameLogic GameLogic
     */
    public Hiscores(Stage stage, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.stage = stage;
    }

    /**
     * Returns hiscores scene.
     * @return Returns hiscores scene
     */
    public Scene getHighscoreScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(500, 500);

        VBox vbox = new VBox();

        vbox.setSpacing(5);

        vbox.getChildren().addAll(getLabels());

        BackgroundSize backgroundSize = new BackgroundSize(350,
                350,
                false,
                false,
                false,
                false);

        BackgroundImage image = new BackgroundImage(new Image(BG_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, backgroundSize);

        vbox.setBackground(new Background(image));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefHeight(200);
        vbox.setPrefWidth(200);
        borderPane.setCenter(vbox);
        borderPane.setTop(header());
        borderPane.setBottom(footer());

        Scene scene = new Scene(borderPane);
        borderPane.setBackground(new Background(getBackgroundImage()));

        return scene;
    }


    /**
     * Returns the header part of the hiscores.
     * @return Returns the header part of the hiscores.
     */
    private HBox header() {
        HBox hBox = new HBox();
        PlatformerLabel headerText = new PlatformerLabel("Hiscores");
        headerText.setTextFill(Color.WHITE);
        hBox.getChildren().addAll(headerText);
        hBox.setPadding(new Insets(50, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    /**
     * Returns the footer that handles transition back to main menu and adds nice styles for it.
     * @return  Returns the footer that handles transition back to main menu and adds nice styles for it.
     */
    private HBox footer() {
        HBox hBox = new HBox();
        PlatformerButton button = new PlatformerButton("MENU");
        button.setOnMousePressed((mouseEvent -> {
            stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        }));
        hBox.getChildren().add(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 10, 0));
        return hBox;
    }

    /**
     * Gets top 10 players from the Database access object and adds them to an list.
     * @return Returns top 10 players
     */
    private ArrayList<PlatformerLabel> getLabels() {
        HiscoresDao dao = new HiscoresDao("hiscores");
        ArrayList<PlatformerLabel> list = new ArrayList<>();
        for (Player player : dao.getScores()) {
            list.add(new PlatformerLabel(player.toString()));
        }
        return list;
    }

    /**
     * Returns the background image.
     * @return Returns the background image.
     */
    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

}
