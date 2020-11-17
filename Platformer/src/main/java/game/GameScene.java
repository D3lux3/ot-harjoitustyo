package game;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import menu.ui.PlatformerButton;
import menu.ui.PlatformerLabel;

public class GameScene {

    private int score;
    public GameScene() {
        this.score = 0;
    }

    public Scene getGameScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(500, 500);
        this.score = 0;
        PlatformerButton button = new PlatformerButton("Press");
        PlatformerLabel label = new PlatformerLabel("" + score);


        button.setOnMouseClicked((event -> {
            this.score++;
            label.setText("" + score);
        }));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, button);
        borderPane.setCenter(vbox);
        Scene scene = new Scene(borderPane);

        return scene;
    }

    public int getScore() {
        return this.score;
    }

    public void clickOnce() {
        this.score++;
    }

}
