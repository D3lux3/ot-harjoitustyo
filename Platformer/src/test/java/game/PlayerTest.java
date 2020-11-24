package game;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player("Jaakko", 14);
    }

    @Test
   public void nameIsCorrect() {
        assertEquals("Jaakko", player.getName());
    }

    @Test
    public void scoreIsCorrect() {
        assertEquals(14, player.getScore());
    }

    @Test
    public void nameSetChangesName() {
        player.setName("Test");
        assertEquals("Test", player.getName());
    }

    @Test
    public void scoreSetChangesScore() {
        player.setScore(100);
        assertEquals(100, player.getScore());
    }

}