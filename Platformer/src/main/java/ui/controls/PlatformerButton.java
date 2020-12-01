package ui.controls;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PlatformerButton extends Button {

    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final String BUTTON_PRESSED =  "-fx-background-color: transparent; -fx-background-image: url('yellow_button.png');";
    private final String BUTTON_FREE = "-fx-background-color: transparent; -fx-background-image: url('yellow_button_pressed.png');";


    public PlatformerButton(String text) {
        setText(text);
        setFont();
        setPrefWidth(190);
        setPrefHeight(46);
        setStyle(BUTTON_FREE);
        initListeners();
    }

    private void setFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setPressedStyle() {
        this.setStyle(BUTTON_PRESSED);
        this.setPrefHeight(43);
        this.setLayoutY(getLayoutY() + 4);
    }

    private void setReleasedStyle() {
        this.setStyle(BUTTON_FREE);
        this.setPrefHeight(47);
        this.setLayoutY(getLayoutY() - 4);
    }

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
