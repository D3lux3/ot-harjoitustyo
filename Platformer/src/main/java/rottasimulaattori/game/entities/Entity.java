package rottasimulaattori.game.entities;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;



public class Entity extends Rectangle {

    public Entity(double x, double y) {
        super(x, y);
        this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
    }

    public void setBGImage(String path) {
        try {
            Image image = new Image(path);
            this.setFill(new ImagePattern(image));
        } catch (Error e) {
            this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

}
