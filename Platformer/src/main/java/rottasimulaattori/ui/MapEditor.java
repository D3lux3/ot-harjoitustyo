package rottasimulaattori.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Level;
import rottasimulaattori.ui.controls.PlatformerButton;

public class MapEditor {


    private Stage stage;
    private GameLogic gameLogic;

    public MapEditor(Stage stage, GameLogic gameLogic) {
        this.stage = stage;
        this.gameLogic = gameLogic;
    }

    public Scene getMapEditor() {
        GridPane gridPane = new GridPane();
        Pane root = new Pane();
        HBox hBox = new HBox();
        gridPane.setBackground(new Background(getBackgroundImage()));
        gridPane.setMaxHeight(500);
        gridPane.setPrefSize(500, 500);

        root.setMaxSize(500,500);
        for (int y = 0; y < Level.firstLevel.length; y++) {
            for (int x = 0; x < Level.firstLevel[y].length(); x++) {
                if (Level.firstLevel[y].charAt(x) == '0') {
                    Button btn = new Button();
                    btn.setMinSize(36,50);
                    btn.setStyle(" -fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
                    btn.setOnMouseClicked((mouseEvent -> {
                        btn.setMinSize(36,50);
                        btn.setStyle("-fx-background-color: transparent; -fx-background-image: url('boxArt.png'); -fx-border-width: 0;");
                    }));
                    gridPane.add(btn, x, y);
                } else if (Level.firstLevel[y].charAt(x) == '1') {
                    Button btn = new Button("");
                    btn.setMinSize(36,50);
                    btn.setOnMouseClicked((mouseEvent -> {
                        btn.setStyle(" -fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
                    }));
                    btn.setStyle("-fx-background-color: transparent; -fx-background-image: url('boxArt.png'); -fx-border-width: 0;");
                    gridPane.add(btn, x, y);
                } else if (Level.firstLevel[y].charAt(x) == 'C') {
                    Button btn = new Button("");
                    btn.setMinSize(52,52);
                    btn.setStyle("-fx-background-color: transparent; -fx-background-image: url('kelaCoin.png'); -fx-border-width: 0;");
                    gridPane.add(btn, x, y);
                } else if (Level.firstLevel[y].charAt(x) == 'G') {
                    Button btn = new Button("");
                    btn.setMinSize(50,64);
                    btn.setStyle("-fx-background-color: transparent; -fx-background-image: url('finish.png'); -fx-border-width: 0;");
                    gridPane.add(btn, x, y);
                }
            }
        }

        PlatformerButton menuButton = new PlatformerButton("Menu");
        menuButton.setOnMouseClicked(event -> {
        this.stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        });

        Button btn = new Button(">");

        btn.setOnMouseClicked(event -> {
            gridPane.setLayoutX(gridPane.getLayoutX() + -10);
        });

        hBox.getChildren().addAll(menuButton, btn);

        root.getChildren().addAll(gridPane, hBox);
        Scene scene = new Scene(root);
        return scene;
    }



    /**
     * Returns the background image.
     * @return
     */
    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }
}
