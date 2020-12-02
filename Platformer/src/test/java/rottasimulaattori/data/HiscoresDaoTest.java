package rottasimulaattori.data;

import rottasimulaattori.game.utils.Player;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class HiscoresDaoTest {

    HiscoresDao hiscoresDao;

    @Before
    public void setUp() {
        this.hiscoresDao = new HiscoresDao("test");
        this.hiscoresDao.clearDB();
    }

    @Test
    public void dbIsEmptyInTheStart() {
        assertEquals(0, hiscoresDao.getScores().size());
    }

    @Test
    public void addingPlayerIncreasesTableSize() {
        hiscoresDao.saveScores(new Player("TestUser", 50));
        assertEquals(1, hiscoresDao.getScores().size());
    }

    @Test
    public void playersAreOrderedByPoints() {
        hiscoresDao.saveScores(new Player("Nuts", 5));
        hiscoresDao.saveScores(new Player("MVP", 500));
        hiscoresDao.saveScores(new Player("Deez", 100));
        hiscoresDao.saveScores(new Player("TestUser", 50));
        assertEquals(4, hiscoresDao.getScores().size());
        assertEquals(new Player("MVP", 500), hiscoresDao.getScores().get(0));
    }

    @AfterClass
    public static void deleteTestDatabase() {
        File f = new File("test.db");
        if (f.exists()) {
            f.delete();
        }
    }

}
