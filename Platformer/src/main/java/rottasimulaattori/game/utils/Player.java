package rottasimulaattori.game.utils;

/**
 * Player class for hiscore saving.
 */
public class Player {

    private String name;
    private int score;

    /**
     * Creates a player object with a name and a score.
     * @param name name
     * @param score score
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        if (getScore() != player.getScore()) {
            return false;
        }

        return getName() != null ? getName().equals(player.getName()) : player.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getScore();
        return result;
    }

    /**
     * Returns player name.
     * @return Returns player name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for the player object.
     * @param name Sets name for the player object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns player score.
     * @return Returns player score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets players score.
     * @param score Sets players score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return this.name + "  " + this.score;
    }
}
