package rottasimulaattori.ui.controls;

import javafx.scene.control.Button;

public class MapEditorTile extends Button {

    private String EMPTY_TILE_STYLE = "-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;";
    private String EMPTY_TILE_STYLE_HOVER = "-fx-border-color: transparent; -fx-background-radius: 0; -fx-background-color: transparent; -fx-border-width: 2; -fx-border-color: black;";
    private String BOX_TILE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('boxArt.png'); -fx-border-width: 0;";
    private String BOX_TILE_STYLE_HOVER = "-fx-background-color: transparent; -fx-background-image: url('boxArt.png'); -fx-border-width: 2; -fx-border-color: black;";
    private String COIN_TILE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('kelaCoin36.png'); -fx-background-repeat: no-repeat; -fx-border-width: 0; ";
    private String COIN_TILE_STYLE_HOVER = "-fx-background-color: transparent; -fx-background-image: url('kelaCoin36.png'); -fx-background-repeat: no-repeat; -fx-border-width: 2; -fx-border-color: black;";
    private String GOAL_TILE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('finish.png'); -fx-border-width: 0;";
    private String GOAL_TILE_STYLE_HOVER = "-fx-background-color: transparent; -fx-background-image: url('finish.png'); -fx-border-width: 2; -fx-border-color: black;";
    private char state;
    private char states[] = new char[]{'0', '1', 'C', 'G'};

    /**
     * Constructor for MapEditorTile.
     * @param c
     */
    public MapEditorTile(char c) {
        this.setMinSize(36, 50);
        this.state = c;
        this.handleStateStyle(c);
        this.initListeners();
    }

    /**
     * Returns the next state. E.g if state is now 0 (Empty) next state would be 1(Box).
     * @return
     */
    private char nextState() {
        for (int i = 0; i < states.length; i++) {
            if (states[i] == this.state) {
                if (i + 1 >= states.length) {
                    return states[0];
                }
                return states[i + 1];
            }
        }
        return '?';
    }

    /**
     * Handles the fx-css change for each state change.
     */
    private void handleStateChange() {
        this.state = nextState();
        this.handleStateStyle(state);
    }

    /**
     * Handles the fx-css change for each state change.
     * @param c
     */
    private void handleStateStyle(char c) {
        switch (c) {
            case '0':
                this.setStyle(EMPTY_TILE_STYLE);
                break;
            case '1':
                this.setStyle(BOX_TILE_STYLE);
                break;
            case 'C':
                this.setStyle(COIN_TILE_STYLE);
                break;
            case 'G':
                this.setStyle(GOAL_TILE_STYLE);
                break;
        }
    }

    /**
     * Sets listeners for the button.
     */
    private void initListeners() {
        this.setOnMouseEntered((event -> {
            switch (state) {
                case '0':
                    this.setStyle(EMPTY_TILE_STYLE_HOVER);
                    break;
                case '1':
                    this.setStyle(BOX_TILE_STYLE_HOVER);
                    break;
                case 'C':
                    this.setStyle(COIN_TILE_STYLE_HOVER);
                    break;
                case 'G':
                    this.setStyle(GOAL_TILE_STYLE_HOVER);
                    break;
            }
        }));

        this.setOnMouseExited((event -> {
            this.handleStateStyle(this.state);
        }));


        this.setOnMouseClicked((event ->  {
            this.handleStateChange();
        }));
    }

    /**
     * Returns current state.
     * @return
     */
    public char getState(){
        return this.state;
    }


}
