package ui;

import data.HiscoresDao;
import game.GameLogic;
import game.utils.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.controls.PlatformerButton;
import ui.controls.PlatformerLabel;

import java.util.ArrayList;


public class Hiscores {

    private final String BG_PATH = "yellow_panel.png";
    private Stage stage;
    private GameLogic gameLogic;

    public Hiscores(Stage stage, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.stage = stage;
    }

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


    private HBox header() {
        HBox hBox = new HBox();
        PlatformerLabel headerText = new PlatformerLabel("Hiscores");
        headerText.setTextFill(Color.WHITE);
        hBox.getChildren().addAll(headerText);
        hBox.setPadding(new Insets(50, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

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

    private ArrayList<PlatformerLabel> getLabels() {
        HiscoresDao dao = new HiscoresDao("hiscores");
        ArrayList<PlatformerLabel> list = new ArrayList<>();
        for (Player player : dao.getScores()) {
            list.add(new PlatformerLabel(player.toString()));
        }
        return list;
    }

    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

}
