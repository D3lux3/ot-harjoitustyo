package rottasimulaattori.game.entities;
/**
 * Ingame Flag entity.
 * It inherits entity.
 * Key element in for players to get bonus points and reach to a goal.
 */
public class GoalEntity extends Entity {

    /**
     * Creates a Flag entity object.
     * Sets proper sprite and sizing to it.
     */
    public GoalEntity(){
        super(50, 64);
        this.setBGImage("finish.png");
    }
}
