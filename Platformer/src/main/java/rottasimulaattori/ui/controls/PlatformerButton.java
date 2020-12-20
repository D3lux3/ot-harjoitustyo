package rottasimulaattori.ui.controls;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Custom button with own styles.
 */
public class PlatformerButton extends Button {

    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED =  "-fx-background-color: transparent; -fx-background-image: url('yellow_button.png');";
    private final String BUTTON_FREE = "-fx-background-color: transparent; -fx-background-image: url('yellow_button_pressed.png');";


    /**
     * Creates a button with custom styles.
     * Takes string as a parameter that is the buttons text.
     * @param text text
     */
    public PlatformerButton(String text) {
        setText(text);
        setFont();
        setPrefWidth(190);
        setPrefHeight(46);
        setStyle(BUTTON_FREE);
        initListeners();
    }

    /**
     * Tries to set the font to custom font. If it fails it loads Verdana font instead.
     */
    private void setFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    /**
     * Handles the style change for pressed button.
     */
    private void setPressedStyle() {
        this.setStyle(BUTTON_PRESSED);
        this.setPrefHeight(43);
        this.setLayoutY(getLayoutY() + 4);
    }

    /**
     * Handles the style change for released button.
     */
    private void setReleasedStyle() {
        this.setStyle(BUTTON_FREE);
        this.setPrefHeight(47);
        this.setLayoutY(getLayoutY() - 4);
    }

    /**
     * Sets listeners for the button.
     */
    private void initListeners() {
       this.setOnMousePressed((event -> {
            this.setPressedStyle();
        }));

        this.setOnMouseReleased((event -> {
            this.setReleasedStyle();
        }));

        this.setOnMouseEntered((event -> {
            this.setEffect(new DropShadow());
        }));

        this.setOnMouseExited((event -> {
            this.setEffect(null);
        }));
    }

}
