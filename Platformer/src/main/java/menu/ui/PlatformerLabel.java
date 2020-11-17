package menu.ui;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlatformerLabel extends Label {
    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    public PlatformerLabel(String text) {
        setText(text);
        setFont();
    }

    private void setFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

}
