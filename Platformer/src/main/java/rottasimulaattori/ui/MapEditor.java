package rottasimulaattori.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rottasimulaattori.game.GameLogic;
import rottasimulaattori.game.utils.Level;
import rottasimulaattori.game.utils.MapHandler;
import rottasimulaattori.ui.controls.MapEditorTile;

import java.io.File;
import java.util.HashMap;

public class MapEditor {

    private HashMap<KeyCode, Boolean> keys;
    private Stage stage;
    private GameLogic gameLogic;
    GridPane gridPane;

    /**
     * Creates MapEditor object
     * @param stage Stage
     * @param gameLogic GameLogic
     */
    public MapEditor(Stage stage, GameLogic gameLogic) {
        this.stage = stage;
        this.gameLogic = gameLogic;
        this.keys = new HashMap<>();
    }

    private void initGridPane() {
        this.gridPane = new GridPane();
        gridPane.setBackground(new Background(getBackgroundImage()));
        gridPane.setMaxHeight(500);
        gridPane.setPrefSize(500, 500);
    }

    /**
     * Returns MapEditor scene
     * @return Returns MapEditor scene
     */
    public Scene getMapEditor() {
        Pane root = new Pane();
        initGridPane();
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
        for (int y = 0; y < Level.level.length; y++) {
            for (int x = 0; x < Level.level[y].length(); x++) {
                Level.setValue(x,y, getNodeFromGridPane(x,y).getState());
            }
        }
    }

    /**
     * Returns specific tile on the gridpane on given coordinates.
     * @param x x
     * @param y y
     * @return Returns specific tile on the gridpane on given coordinates.
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
        this.gridPane.getChildren().clear();
        for (int y = 0; y < Level.level.length; y++) {
            for (int x = 0; x < Level.level[y].length(); x++) {
                MapEditorTile tile = new MapEditorTile(Level.level[y].charAt(x));
                gridPane.add(tile, x,y);
            }
        }
    }

    /**
     * Returns boolean if given key is pressed
     * @param k k
     * @return Returns boolean if given key is pressed
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
     * @return Returns the background image.
     */
    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

    /**
     *
     * Returns the up left corner menu.
     * @return Returns the up left corner menu.
     */
    private HBox menuHBox() {
        HBox hBox = new HBox();
        Button menuButton = new Button("Menu");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        menuButton.setOnMouseClicked(event -> {
            this.handleLevelEdit();
            this.stage.setScene(new Menu(stage, gameLogic).getMenuScene());
        });

        saveButton.setOnMouseClicked(event -> {
            this.handleLevelEdit();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add( new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));

            File file = fc.showSaveDialog(stage);

            if (file != null) {
                MapHandler mapHandler = new MapHandler();
                mapHandler.saveLevel(file);
            } else {
                return;
            }
        });

        loadButton.setOnMouseClicked(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Open a map");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));

            File file = fc.showOpenDialog(stage);
            if (file != null) {
                MapHandler mapHandler = new MapHandler();
                try {
                    mapHandler.loadMap(file);
                    this.setTiles();
                    this.handleLevelEdit();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Loaded map is either corrupt or wrong format.").show();
                }
            }

        });

        hBox.getChildren().addAll(menuButton, saveButton, loadButton);
        return hBox;
    }
}
