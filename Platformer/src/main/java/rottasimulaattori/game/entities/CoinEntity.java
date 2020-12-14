package rottasimulaattori.game.entities;

/**
 * Ingame Coin entity.
 * It inherits entity.
 * Key element in for players to get score ingame.
 */
public class CoinEntity extends Entity {

    /**
     * Creates CoinEntity object. With given width and height.
     * Adds 2d coin sprite on creation.
     * @param width
     * @param height
     */
    public CoinEntity(double width , double height) {
        super(width, height);
        this.setBGImage("kelaCoin.png");
    }


}
