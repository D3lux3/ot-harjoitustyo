package rottasimulaattori.game.Entities;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PlayerEntityTest {

    PlayerEntity player;
    List<Entity> platforms;

    @Before
    public void setUp() {
        this.player = new PlayerEntity(1,1, true);
        this.player.setTranslateY(2);
        this.player.setTranslateX(0);

        this.platforms = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            Entity platform = new Entity(1,1);
            platform.setTranslateY(1);
            platform.setTranslateX(x);
            platforms.add(platform);
        }
    }

    @Test
    public void playerMovesRight(){
        this.player.moveX(1, platforms);
        assertEquals(1.0, this.player.getTranslateX(), 0);
    }

    @Test
    public void playerMovesLeft(){
        this.player.moveX(-1, platforms);
        assertEquals(-1.0, this.player.getTranslateX(), 0);
    }

    @Test
    public void playerMovesUp(){
        this.player.moveY(-1, platforms);
        assertEquals(1.0, this.player.getTranslateY(), 0);
    }

    @Test
    public void playerMovesDown(){
        this.player.moveY(1, platforms);
        assertEquals(3.0, this.player.getTranslateY(), 0);
    }

    @Test
    public void playerJump(){
        this.player.jump();
        assertEquals(-30.0, this.player.getVelocity().getY(), 0);
    }

    @Test
    public void settingPlayerVelocity() {
        this.player.setPlayerVelocity(1,2);
        assertEquals(1.0, this.player.getVelocity().getX(), 0);
        assertEquals(2.0, this.player.getVelocity().getY(), 0);
    }

}