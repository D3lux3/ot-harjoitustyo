package rottasimulaattori.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import rottasimulaattori.game.GameLogic;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import rottasimulaattori.game.entities.CoinEntity;
import rottasimulaattori.game.entities.Entity;
import rottasimulaattori.game.entities.GoalEntity;
import rottasimulaattori.game.utils.Level;
import rottasimulaattori.game.entities.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameScene {

    private GameLogic gameLogic;
    private HashMap<KeyCode, Boolean> keys;
    private Pane uiRoot = new Pane();
    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private PlayerEntity player;
    private List<Entity> platforms;
    private List<CoinEntity> coins;
    private Label scoreLabel;
    private int levelWidth;
    private int levelHeight;
    private List<GoalEntity> goals;

    /**
     * Constructor that initializes the game. Requires a gamelogic as parameter.
     * @param gameLogic GameLogic
     */
    public GameScene(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.keys = new HashMap<>();
        this.platforms = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.scoreLabel = new Label("Score: " + this.gameLogic.getScore());
        this.scoreLabel.setTextFill(Color.WHITE);
        this.goals = new ArrayList<>();
    }

    /**
     * Handles level generation and initializes the content.
     * 0 = Empty
     * 1 = Platform
     * C = Coin
     * G = Goal
     */
    private void initContent() {
        Entity bg = new Entity(500,500);
        bg.setBGImage("backgroundMenu.png");
        levelWidth = Level.level[0].length() * 60;
        levelHeight = Level.level.length * 60;
        for (int i = 0; i < Level.level.length; i++) {
            String line = Level.level[i];
            for (int j = 0; j < line.length(); j++) {
                switch(line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Entity platform = createEntity(j*36, i*50, 36, 50, "boxArt.png");
                        platforms.add(platform);
                        break;
                    case 'C':
                        coins.add(this.createCoinEntity(j * 36, i * 50));
                        break;
                    case 'G':
                        this.goals.add(this.createGoalEntity(j * 36, i * 50));
                }
            }
        }
        this.player = this.createPlayerEntity(0, 450);
        playerCameraX();
        playerCameraY();
        appRoot.getChildren().addAll(bg, gameRoot, uiRoot, scoreLabel);
    }



    /**
     * Handles camera changes on x axis.
     */
    private void playerCameraX() {
        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 250 && offset < levelWidth - 250) {
                gameRoot.setLayoutX(-(offset - 250));
            }
        });
    }

    /**
     * Handles camera changes on y axis.
     */
    private void playerCameraY() {
        player.translateYProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 250 && offset < levelHeight - 250) {
                gameRoot.setLayoutY(-(offset - 250));
            }
        });
    }

    /**
     * Check if key is pressed, if key is not present in the map. Returns false.
     * @param key Pressed key
     * @return Returns if key is pressed
     */
    private boolean keyPressed(KeyCode key) {
        return this.keys.getOrDefault(key, false);
    }

    /**
     * Creates a Entity object to given coordinates with given width and height. Also requires path to 2d sprite.
     * @param x x
     * @param y y
     * @param w w
     * @param h h
     * @param path File path
     * @return Returns Entity
     */
    private Entity createEntity(int x, int y, int w, int h, String path) {
        Entity entity = new Entity(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setBGImage(path);
        gameRoot.getChildren().add(entity);
        return entity;
    }

    /**
     * Creates a Coinentity to given parameters.
     * @param x x
     * @param y y
     * @return Returns CoinEntity
     */
    private CoinEntity createCoinEntity(int x, int y) {
        CoinEntity coinEntity = new CoinEntity(50,50);
        coinEntity.setTranslateX(x);
        coinEntity.setTranslateY(y);
        gameRoot.getChildren().add(coinEntity);
        return coinEntity;
    }


    /**
     * Creates a GoalEntity to given parameters.
     * @param x x
     * @param y y
     * @return Returns GoalEntity
     */
    private GoalEntity createGoalEntity(int x, int y) {
        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setTranslateX(x);
        goalEntity.setTranslateY(y);
        gameRoot.getChildren().add(goalEntity);
        return goalEntity;
    }


    /**
     * Creates and returns a player entity to given coordinates..
     * @param x x
     * @param y y
     * @return Returns PlayerEntity
     */
    private PlayerEntity createPlayerEntity(int x, int y) {
        PlayerEntity playerEntity = new PlayerEntity(40, 40);
        playerEntity.setTranslateX(x);
        playerEntity.setTranslateY(y);
        gameRoot.getChildren().add(playerEntity);
        return playerEntity;
    }

    /**
     * Checks if player has dropped from the level and handles game ending.
     * @return Returns if player is alive
     */
    private boolean playerAlive() {
        if (player.getTranslateY() > levelHeight) {
            this.player.killPlayer();
            this.gameOver();
            return false;
        }
        return true;
    }

    /**
     * Handles all key checks and gravity.
     */
    private void update() {
        scoreLabel.setText("Score: " + this.gameLogic.getScore());
        if ((keyPressed(KeyCode.UP) || keyPressed(KeyCode.W) || keyPressed(KeyCode.SPACE)) && player.getTranslateY() >= 5) {
            this.player.jump();
        }
        if ((keyPressed(KeyCode.LEFT) ||  keyPressed(KeyCode.A)) && player.getTranslateX() >= 5) {
            this.player.moveX(-5, platforms);
        }

        if ((keyPressed(KeyCode.RIGHT) ||keyPressed(KeyCode.D))  && player.getTranslateX() + 40 <= levelWidth - 5) {
            this.player.moveX(5, platforms);
        }

        if (this.player.getVelocity().getY() < 10) {
            this.player.setPlayerVelocity(0, 1);
        }
        this.player.moveY((int)this.player.getVelocity().getY(), platforms);
        checkForCoinCollision();
        checkForGoalCollision();
    }


    /**
     * Checks for collision between the player and a coin.
     */
    private void checkForCoinCollision() {
        for (int i = 0; i < coins.size(); i++) {
            CoinEntity coin = coins.get(i);
            if (player.getBoundsInParent().intersects(coin.getBoundsInParent())) {
                gameRoot.getChildren().remove(coin);
                this.coins.remove(i);
                this.gameLogic.addScore();
            }
        }
    }

    /**
     * Check for collision between player and the goal.
     */
    private void checkForGoalCollision() {
        if (this.goals.isEmpty()) {
            return;
        }

        for (GoalEntity g : this.goals) {
            if (player.getBoundsInParent().intersects(g.getBoundsInParent())){
                this.gameLogic.addScore(10);
                this.player.killPlayer();
                this.gameOver();
            }
        }
    }

    /**
     * Handles the scene changes on player death.
     */
    private void gameOver() {
        EndGameScene endGameScene = new EndGameScene(gameLogic, gameLogic.getStage());
        this.gameLogic.changeScene(endGameScene.getEndGameScene(this.gameLogic.getScore()));
    }

    /**
     * Returns the gamescene.
     * @return Returns GameScene
     */
    public Scene getGameScene() {
        initContent();
        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed((event -> {
            keys.put(event.getCode(), true);
        }));
        scene.setOnKeyReleased((event -> {
            keys.put(event.getCode(), false);
        }));
        AnimationTimer anim = new AnimationTimer() {
            long previous = 0;
            @Override
            public void handle(long l) {
                if (l - previous < 1000000000 / 100) {
                    return;
                }
                update();
                if (!playerAlive() || !player.getAlive()) {
                    this.stop();
                }

                previous = l;
            }
        };
        anim.start();
        return scene;
    }


}
