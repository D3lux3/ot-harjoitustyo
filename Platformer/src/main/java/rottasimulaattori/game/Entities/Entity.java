package rottasimulaattori.game.Entities;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;



public class Entity extends Rectangle {

    public Entity(double x, double y) {
        super(x,y);
        this.setFill(Color.GREEN);
    }

    public void setBGImage(String path) {
        Image image = new Image(path);
        this.setFill(new ImagePattern(image));
    }

}