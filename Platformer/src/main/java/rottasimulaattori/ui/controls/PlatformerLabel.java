package rottasimulaattori.ui.controls;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Custom label.
 */
public class PlatformerLabel extends Label {
    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    /**
     * Custom label with custom font.
     * Sets labels text to parameter.
     * @param text
     */
    public PlatformerLabel(String text) {
        setText(text);
        setFont();
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

}
