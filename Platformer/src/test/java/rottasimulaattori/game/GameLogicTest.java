package rottasimulaattori.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {

    GameLogic gameLogic;
    @Before
    public void setUp() {
        gameLogic = new GameLogic();
    }


    @Test
    public void addingCustomScoreIncreasesScore() {
        gameLogic.addScore(50);
        assertEquals(50, gameLogic.getScore());
    }

    @Test
    public void clickingIncreasesScore() {
        gameLogic.addScore();
        assertEquals(1, gameLogic.getScore());
    }

    @Test
    public void resetingScoreResets() {
        gameLogic.addScore();
        gameLogic.addScore();
        gameLogic.resetScore();
        assertEquals(0, gameLogic.getScore());
    }
}
