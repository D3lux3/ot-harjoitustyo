package rottasimulaattori.game.entities;

import javafx.geometry.Point2D;

import java.util.List;

/**
 * PlayerEntity is the playable player object ingame.
 * This class handles all player movement and animations.
 */
public class PlayerEntity extends Entity {

    private boolean canJump;
    private Point2D playerVelocity;
    private boolean noImg;
    private boolean alive;

    /**
     * Constructor that creates PlayerEntity with given width and height parameters.
     * It sets 2d sprite for the player in this constructor.
     * @param width
     * @param height
     */
    public PlayerEntity(double width, double height) {
        super(width, height);
        this.setBGImage("rottapng.png");
        this.playerVelocity  = new Point2D(0, 0);
        this.canJump = true;
        this.alive = true;
    }

    /**
     * Constructor that creates PlayerEntity with given width and height parameters.
     * This constructor is mainly only for testing purposes only, because JavaFx and JUnit doesn't get along.
     * This constructor skips loading of the 2d sprite and uses random RGB color instead.
     * @param width
     * @param height
     * @param nobg
     */
    public PlayerEntity(double width, double height, boolean nobg) {
        super(width, height);
        this.noImg = nobg;
        this.playerVelocity  = new Point2D(0, 0);
        this.canJump = true;
        this.alive = true;
    }

    /**
     * Changes 2d sprite depending on player moving direction.
     * If player moves right it sets 2d sprite to face right and vice versa.
     * @param movingRight
     */
    private void handleAnimation(boolean movingRight) {
        if (movingRight) {
            this.handleRightAnimation();
        } else {
            this.handleLeftAnimation();
        }
    }

    /**
     * Handles the 2d sprite change to left.
     */
    private void handleLeftAnimation() {
        if (noImg) {
            return;
        }
        this.setBGImage("rottapngLeft.png");
    }
    /**
     * Handles the 2d sprite change to right.
     */
    private void handleRightAnimation() {
        if (noImg) {
            return;
        }
        this.setBGImage("rottapng.png");
    }

    /**
     * Moves player given value on X axis either left or right.
     * It takes all the platforms as a parameter and checks for collisions.
     * @param value
     * @param platforms
     */
    public void moveX(int value, List<Entity> platforms) {
        boolean movingRight = value > 0;
        this.handleAnimation(movingRight);
        for (int i = 0; i < Math.abs(value); i++) {
            for (Entity platform : platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (this.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    } else {
                        if (this.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    /**
     * Moves player given value on Y axis either up or down.
     * It takes all the platforms as a parameter and checks for collisions.
     * @param value
     * @param platforms
     */
    public void moveY(int value, List<Entity> platforms) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Entity platform : platforms) {
                if (this.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (this.getTranslateY() + 40 == platform.getTranslateY()) {
                            this.setTranslateY(this.getTranslateY() - 1);
                            this.canJump = true;
                            return;
                        }
                    }
                    if (this.getTranslateY() == platform.getTranslateY() + 60) {
                        return;
                    }

                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    /**
     * Handles the player jumping. Default value for optimal jump height is -30.
     */
    public void jump() {
        if (canJump) {
            playerVelocity = playerVelocity.add(0, -30);
            canJump = false;
        }
    }

    /**
     * Returns player velocity.
     * @return
     */
    public Point2D getVelocity() {
        return this.playerVelocity;
    }

    /**
     * Sets players velocity.
     * @param x
     * @param y
     */
    public void setPlayerVelocity(double x, double y) {
        playerVelocity = playerVelocity.add(x, y);
    }

    /**
     * Sets players alive boolean value to false.
     */
    public void killPlayer() {
        this.alive = false;
    }

    /**
     * Gets alive boolean.
     * @return
     */
    public boolean getAlive() {
        return this.alive;
    }
}
