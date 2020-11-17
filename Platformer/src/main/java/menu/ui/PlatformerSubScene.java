package menu.ui;

import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class PlatformerSubScene extends SubScene {

    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final String BG_PATH = "yellow_panel.png";


    public PlatformerSubScene() {
        super(new AnchorPane(), 200, 200);
        prefHeight(200);
        prefWidth(200);

        BackgroundImage image = new BackgroundImage(new Image(BG_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);


        AnchorPane pane = (AnchorPane) this.getRoot();

        pane.setBackground(new Background(image));
    }


    public void closeAndOpen() {
        this.getRoot().setVisible(!this.getRoot().visibleProperty().get());
    }
}
