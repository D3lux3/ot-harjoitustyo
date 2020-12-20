package rottasimulaattori.game;

import org.junit.Before;
import org.junit.Test;
import rottasimulaattori.game.utils.Level;

import static org.junit.Assert.*;


public class LevelTest {
    @Before
    public void setUp() {
        Level.level = new String[] {"000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000C0000000000000000000000000000000000C0000000000000000000000000000000000000000000000C00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "000001000000000000000000000000000000000010000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000C000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000C000000000000000000000000000000000000000000000000",
                "000000000000000000000000000000000000000000000000000000000000C00000000000000011000000000000001111000000000000000000000000000000000001000000000000000000000000000000000000000000000000",
                "00011100000000000000000000000000000010000000000C00000000000010000000000000000000000000000000000000000000000000000000000000000000000000G000000000000000000000000000000000000000000000",
                "000000001110000000000000000000000001000000000001000000100000000000011110000000000000000100000000000011000000000000000000000000100000001000000000000000000000000000000000000000000000",
                "0000000C00000111000000000000000000000000000000100000000001100011000000000000C0000100000000000000000000001000000000000000010000000000000000000000000000000000000000000000000000000000",
                "000000010000000000011100011000000011100011111000001110000000000000000110000010000000000100000000000000001100000000001000000000000000000000000000000000000000000000000000000000000000",
                "111111110011110001111100111111000100000000000000000000000000000000000000000000000000000000000000000000000000111100000000000000000000000000000000000000000000000000000000000000000000"};
    }

    @Test
    public void getCharAtReturnsRightChar() {
        assertEquals('0', Level.getValue(0,0));
    }

    @Test
    public void setCharAtReturnsRightChar() {
        assertEquals('0', Level.getValue(0,0));
        Level.setValue(0,0, '1');
        assertEquals('1', Level.getValue(0,0));
    }

    @Test
    public void levelInMemoryIsRightOne() {
        String[] level = new String[] {
                "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000C0000000000000000000000000000000000C0000000000000000000000000000000000000000000000C00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "000001000000000000000000000000000000000010000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000C000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                "00000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000C000000000000000000000000000000000000000000000000",
                "000000000000000000000000000000000000000000000000000000000000C00000000000000011000000000000001111000000000000000000000000000000000001000000000000000000000000000000000000000000000000",
                "00011100000000000000000000000000000010000000000C00000000000010000000000000000000000000000000000000000000000000000000000000000000000000G000000000000000000000000000000000000000000000",
                "000000001110000000000000000000000001000000000001000000100000000000011110000000000000000100000000000011000000000000000000000000100000001000000000000000000000000000000000000000000000",
                "0000000C00000111000000000000000000000000000000100000000001100011000000000000C0000100000000000000000000001000000000000000010000000000000000000000000000000000000000000000000000000000",
                "000000010000000000011100011000000011100011111000001110000000000000000110000010000000000100000000000000001100000000001000000000000000000000000000000000000000000000000000000000000000",
                "111111110011110001111100111111000100000000000000000000000000000000000000000000000000000000000000000000000000111100000000000000000000000000000000000000000000000000000000000000000000"
        };
        assertEquals(level, Level.level);
        Level.setValue(0,0, '1');
        assertNotEquals(level, Level.level);
    }

}