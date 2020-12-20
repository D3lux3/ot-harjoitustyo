package rottasimulaattori.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Level;
import rottasimulaattori.ui.controls.MapEditorTile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MapEditor {

    private HashMap<KeyCode, Boolean> keys;
    private Stage stage;
    private GameLogic gameLogic;
    GridPane gridPane;

    /**
     * Creates MapEditor object
     * @param stage
     * @param gameLogic
     */
    public MapEditor(Stage stage, GameLogic gameLogic) {
        this.stage = stage;
        this.gameLogic = gameLogic;
        this.gridPane = new GridPane();
        this.keys = new HashMap<>();
    }

    /**
     * Returns MapEditor scene
     * @return
     */
    public Scene getMapEditor() {
        Pane root = new Pane();
        gridPane.setBackground(new Background(getBackgroundImage()));
        gridPane.setMaxHeight(500);
        gridPane.setPrefSize(500, 500);
        setTiles();

        root.setMaxSize(500,600);
        root.getChildren().addAll(gridPane, menuHBox());

        Scene scene = new Scene(root);

        scene.setOnKeyPressed((event -> {
            keys.put(event.getCode(), true);
        }));
        scene.setOnKeyReleased((event -> {
            keys.put(event.getCode(), false);
        }));
        AnimationTimer anim = new AnimationTimer() {
            long previous = 0;
            @Override
            public void handle(long l) {
                if (l - previous < 1000000000 / 100) {
                    return;
                }
                update();
                previous = l;
            }
        };
        anim.start();

        return scene;
    }

    /**
     * Saves the level edits.
     */
    private void handleLevelEdit() {
        for (int y = 0; y < Level.firstLevel.length; y++) {
            for (int x = 0; x < Level.firstLevel[y].length(); x++) {
                Level.setValue(x,y, getNodeFromGridPane(x,y).getState());
            }
        }
    }

    /**
     * Returns specific tile on the gridpane on given coordinates.
     * @param x
     * @param y
     * @return
     */
    private MapEditorTile getNodeFromGridPane(int x, int y) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (MapEditorTile) node;
            }
        }
        return null;
    }

    /**
     * Sets MapEditorTiles on all level coordinates.
     */
    private void setTiles() {
        for (int y = 0; y < Level.firstLevel.length; y++) {
            for (int x = 0; x < Level.firstLevel[y].length(); x++) {
                MapEditorTile tile = new MapEditorTile(Level.firstLevel[y].charAt(x));
                gridPane.add(tile, x,y);
            }
        }
    }

    /**
     * Returns boolean if given key is pressed
     * @param k
     * @return
     */
    private boolean isPressed(KeyCode k) {
        return this.keys.getOrDefault(k, false);
    }


    /**
     * Moves camera to the right.
     */
    private void moveCameraRight() {
        this.gridPane.setLayoutX(gridPane.getLayoutX() - 10);
    }

    /**
     * Moves camera to the left.
     */
    private void moveCameraLeft() {
        this.gridPane.setLayoutX(gridPane.getLayoutX() + 10);
    }

    /**
     * Moves camera to the down.
     */
    private void moveCameraDown() {
        this.gridPane.setLayoutY(gridPane.getLayoutY() - 10);
    }

    /**
     * Moves camera to the up.
     */
    private void moveCameraUp() {
        this.gridPane.setLayoutY(gridPane.getLayoutY() + 10);
    }


    /**
     * Handles free camera movement.
     */
    private void update() {
        if (isPressed(KeyCode.RIGHT) || isPressed(KeyCode.D) ) {
            this.moveCameraRight();
        }
        if (isPressed(KeyCode.A)  || isPressed(KeyCode.LEFT)) {
            this.moveCameraLeft();
        }
        if(isPressed(KeyCode.W)  || isPressed(KeyCode.UP)) {
            this.moveCameraUp();
        }
        if(isPressed(KeyCode.S)  || isPressed(KeyCode.DOWN)) {
            this.moveCameraDown();
        }
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

    /**
     *
     * Returns the up left corner menu.
     * @return
     */
    private HBox menuHBox() {
        HBox hBox = new HBox();
        Button menuButton = new Button("Menu");
        Button saveButton = new Button("Save");
        menuButton.setOnMouseClicked(event -> {
            this.handleLevelEdit();
            this.stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        });
        hBox.getChildren().addAll(menuButton, saveButton);
        return hBox;
    }
}
