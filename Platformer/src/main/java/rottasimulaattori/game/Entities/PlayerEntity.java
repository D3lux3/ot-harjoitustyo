package rottasimulaattori.game.Entities;

import javafx.geometry.Point2D;

import java.util.List;

public class PlayerEntity extends Entity {

    private boolean canJump;
    private Point2D playerVelocity;

    public PlayerEntity(double x, double y) {
        super(x, y);
        this.setBGImage("rottapng.png");
        this.playerVelocity  = new Point2D(0,0);
        this.canJump = true;
    }

    private void handleLeftAnimation() {
        this.setBGImage("rottapngLeft.png");
    }

    private void handleRightAnimation() {
        this.setBGImage("rottapng.png");
    }

    public void moveX(int value, List<Entity> platforms) {
        boolean movingRight = value > 0;
        if (movingRight) {
            this.handleRightAnimation();
        } else {
            this.handleLeftAnimation();
        }
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
                    } else {
                        if (this.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            this.setTranslateY(this.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    public void jump() {
        if (canJump) {
            playerVelocity = playerVelocity.add(0, -30);
            canJump = false;
        }
    }

    public Point2D getVelocity() {
        return this.playerVelocity;
    }

    public void setPlayerVelocity(double x, double y) {
       playerVelocity = playerVelocity.add(x, y);
    }
}
