package menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ui.PlatformerButton;
import menu.ui.PlatformerLabel;

import java.util.ArrayList;


public class Hiscores {

    private final String BG_PATH = "yellow_panel.png";
    private Stage stage;

    public Hiscores(Stage stage) {
        this.stage = stage;
    }

    public Scene getHighscoreScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(500, 500);

        VBox vbox = new VBox();

        vbox.setSpacing(5);

        vbox.getChildren().addAll(getLabels());

        BackgroundSize backgroundSize = new BackgroundSize(350,
                350,
                false,
                false,
                false,
                false);

        BackgroundImage image = new BackgroundImage(new Image(BG_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, backgroundSize);

        vbox.setBackground(new Background(image));
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefHeight(200);
        vbox.setPrefWidth(200);
        borderPane.setCenter(vbox);
        borderPane.setTop(header());
        borderPane.setBottom(footer());

        Scene scene = new Scene(borderPane);
        borderPane.setBackground(new Background(getBackgroundImage()));

        return scene;
    }


    private HBox header() {
        HBox hBox = new HBox();
        PlatformerLabel headerText = new PlatformerLabel("Hiscores");
        headerText.setTextFill(Color.WHITE);
        hBox.getChildren().addAll(headerText);
        hBox.setPadding(new Insets(50, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private HBox footer() {
        HBox hBox = new HBox();
        PlatformerButton button = new PlatformerButton("MENU");
        button.setOnMousePressed((mouseEvent -> {
            stage.setScene(new Menu(stage).getMenuScene());
        }));
        hBox.getChildren().add(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 10, 0));
        return hBox;
    }

    private ArrayList<PlatformerLabel> getLabels() {
        String[] data = new String[]{"Dani;50", "Patrick;30", "Kani;100",
                "Anni;5", "Haisu;0", "Kit;12", "Mummo;34", "Pappa;0", "Hattu;12", "jdfgh;34"};
        ArrayList<PlatformerLabel> list = new ArrayList<>();
        for (String string : data) {
            String[] splitted =  string.split(";");
            list.add(new PlatformerLabel(splitted[0] + " \t " + splitted[1]));
        }
        return list;
    }

    private BackgroundImage getBackgroundImage() {
        Image bgImage = new Image("backgroundMenu.png");
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return backgroundImage;
    }

}
