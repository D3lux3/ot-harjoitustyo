package ui;

import game.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.controls.PlatformerButton;
import ui.controls.PlatformerSubScene;

public class Menu {

    private PlatformerButton startButton;
    private PlatformerButton highscoreButton;
    private PlatformerButton exitButton;
    private Stage stage;
    private GameLogic gameLogic;

    public Menu(Stage stage, GameLogic gameLogic) {
        this.stage = stage;
        this.gameLogic = gameLogic;
        this.startButton = new PlatformerButton("START");
        this.highscoreButton = new PlatformerButton("HISCORES");
        this.exitButton = new PlatformerButton("EXIT");
        setButtonHandlers();
    }

    private void setButtonHandlers() {
        this.startButton.setOnMousePressed((event -> {
            stage.setScene(new GameScene(gameLogic).getGameScene());
        }));

        exitButton.setOnMousePressed((event -> {
            System.exit(0);
        }));

        highscoreButton.setOnMousePressed((event -> {
            stage.setScene(new Hiscores(stage, gameLogic).getHighscoreScene());
        }));

    }

    public Scene getMenuScene() {
        BorderPane borderPane = new BorderPane();
        VBox buttons = new VBox();
        buttons.getChildren().addAll(startButton,
                highscoreButton,
                exitButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);

        VBox logoBox = new VBox();
        logoBox.getChildren().add(getLogo());
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setTranslateX(logoBox.getTranslateX() + 25);
        logoBox.setPadding(new Insets(50));

        PlatformerSubScene sub = new PlatformerSubScene();

        startButton.setOnMouseClicked((event -> {
            sub.closeAndOpen();
        }));

        borderPane.setTop(logoBox);
        borderPane.setCenter(buttons);
        borderPane.setBackground(new Background(getBackgroundImage()));
        return new Scene(borderPane, 500, 500);
    }

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

    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

}
