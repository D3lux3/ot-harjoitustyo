package rottasimulaattori.game.entities;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 * Ingame entity.
 * It serves as a base class for ingame object.
 * If you plan to make new entities, just inherit this.
 */
public class Entity extends Rectangle {

    /**
     * Creates an entity with given width and height.
     * Color of the object will be set at random.
     * @param width Width
     * @param height Height
     */
    public Entity(double width, double height) {
        super(width, height);
        this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
    }

    /**
     * Sets a image for the entity.
     * Custom 2d sprites should be used using this class.
     * @param path Filepath
     */
    public void setBGImage(String path) {
        try {
            Image image = new Image(path);
            this.setFill(new ImagePattern(image));
        } catch (Error e) {
            this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        }
    }

}
