package rottasimulaattori.ui;

import rottasimulaattori.game.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rottasimulaattori.ui.controls.PlatformerButton;

/**
 * Menu scene of the GUI.
 */
public class Menu {

    private PlatformerButton startButton;
    private PlatformerButton highscoreButton;
    private PlatformerButton mapEditorButton;
    private PlatformerButton exitButton;
    private Stage stage;
    private GameLogic gameLogic;

    /**
     * Creates Menu object.
     * @param stage Stage
     * @param gameLogic GameLogic
     */
    public Menu(Stage stage, GameLogic gameLogic) {
        this.stage = stage;
        this.gameLogic = gameLogic;
        this.startButton = new PlatformerButton("START");
        this.mapEditorButton = new PlatformerButton("EDITOR");
        this.highscoreButton = new PlatformerButton("HISCORES");
        this.exitButton = new PlatformerButton("EXIT");
        setButtonHandlers();
    }

    /**
     * Sets handlers for all the buttons.
     */
    private void setButtonHandlers() {
        this.startButton.setOnMousePressed((event -> {
            stage.setScene(new GameScene(gameLogic).getGameScene());
        }));

        this.mapEditorButton.setOnMousePressed((event -> {
            stage.setScene(new MapEditor(stage, gameLogic).getMapEditor());
        }));

        exitButton.setOnMousePressed((event -> {
            System.exit(0);
        }));

        highscoreButton.setOnMousePressed((event -> {
            stage.setScene(new Hiscores(stage, gameLogic).getHighscoreScene());
        }));

    }

    /**
     * Returns the menu scene.
     * @return Returns the menu scene.
     */
    public Scene getMenuScene() {
        BorderPane borderPane = new BorderPane();
        VBox buttons = new VBox();
        buttons.getChildren().addAll(startButton,
                mapEditorButton,
                highscoreButton,
                exitButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);

        VBox logoBox = new VBox();
        logoBox.getChildren().add(getLogo());
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(logoBox.getTranslateX() + 25);
        logoBox.setPadding(new Insets(50));
        borderPane.setTop(logoBox);
        borderPane.setCenter(buttons);
        borderPane.setBackground(new Background(getBackgroundImage()));
        return new Scene(borderPane, 500, 500);
    }

    /**
     * Returns the pixelated Kela logo.
     * @return Returns the pixelated Kela logo.
     */
    private ImageView getLogo() {
        ImageView logo = new ImageView("logo.png");

        logo.setOnMouseEntered((event -> {
            logo.setEffect(new DropShadow());
        }));
        logo.setOnMouseExited((event -> {
            logo.setEffect(null);
        }));
        return logo;
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
