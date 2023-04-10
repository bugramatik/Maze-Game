package Core;

import Actors.*;
import Components.*;
import Util.AABB;
import Util.GameMapLoader;

import java.awt.*;
import java.util.ArrayList;

public class GameEngine {
    // Game Objects
    private static Player player;
    // Concrete Types of the game
    private static ArrayList<Wall> walls;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<PowerUp> powerUps;
    private static ArrayList<Bullet> bulletsInCirculation;
    // Add extra components if you like
    private static ArrayList<IRealTimeComponent> miscComponents;
    private final Dimension screenSize;
    private final String currentMap;

    public GameEngine(String mapFilePath, Dimension screenSize) {
        this.currentMap = mapFilePath;
        this.screenSize = screenSize;

        this.walls = new ArrayList<Wall>();
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUp>();
        this.bulletsInCirculation = new ArrayList<Bullet>();
        this.miscComponents = new ArrayList<IRealTimeComponent>();


        ResetGame();
    }

    public static Player getPlayer() {
        return player;
    }

    public static ArrayList<Wall> getWalls() {
        return walls;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public static void createBullet(Bullet bullet) {
        bulletsInCirculation.add(bullet);
    }

    private void ResetGame() {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();
        if (player != null) {
            player.kill();
        }
        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);

        if (!mapOK) {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

        AABB loadedPlayer = map.getLoadedPlayerAABB();

        if (player == null) {
            player = new Player(loadedPlayer.getPos(), loadedPlayer.getSizeX(), loadedPlayer.getSizeY());
        } else {
            player.getPos().x = loadedPlayer.getPos().x;
            player.getPos().y = loadedPlayer.getPos().y;
        }

        PlayerInputComponent tempInputComponent = new PlayerInputComponent(player);
        GameWindow.GetInstance().attachKeyListener(tempInputComponent);
        miscComponents.add(tempInputComponent);

        CollisionComponent tempPlayerCollisionComponent = new CollisionComponent(player);
        miscComponents.add(tempPlayerCollisionComponent);
        tempPlayerCollisionComponent.events.subscribe("Wall", player);
        tempPlayerCollisionComponent.events.subscribe("Enemy", player);
        tempPlayerCollisionComponent.events.subscribe("PowerUp", player);


        map.getLoadedWallAABBs().forEach(wall -> walls.add(new Wall(wall.getPos(), wall.getSizeX(), wall.getSizeY())));

        map.getLoadedEnemyYAABBs().forEach(enemy -> {
            Enemy tempEnemy = new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(), new VerticalPatrolStrategy());
            enemies.add(tempEnemy);
            CollisionComponent tempCollisionComponent = new CollisionComponent(tempEnemy);
            miscComponents.add(tempCollisionComponent);
            tempCollisionComponent.events.subscribe("Wall", tempEnemy);
        });

        map.getLoadedEnemyXAABBs().forEach(enemy -> {
            Enemy tempEnemy = new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(), new HorizontalPatrolStrategy());
            enemies.add(tempEnemy);
            CollisionComponent tempCollisionComponent = new CollisionComponent(tempEnemy);
            miscComponents.add(tempCollisionComponent);
            tempCollisionComponent.events.subscribe("Wall", tempEnemy);
        });

        map.getLoadedEnemyStationaryAABBs().forEach(enemy -> enemies.add(new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(), new StationaryPatrolStrategy())));
        map.getLoadedPowerUpAABBs().forEach(powerUp -> powerUps.add(new PowerUp(powerUp.getPos(), powerUp.getSizeX(), powerUp.getSizeY())));

    }

    public synchronized void update(float deltaT, Graphics2D currentDrawBuffer) {
        // ==================================== //
        // YOU SHOULD NOT CHANGE THIS FUNCTION  //
        // ============================================= //
        // THIS SHOULD ALREADY DOES EVERYTHING YOU NEED  //
        // ============================================= //
        // You can still change it though with a penalty.

        // Do update first
        walls.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        enemies.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        powerUps.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        bulletsInCirculation.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        player.update(deltaT, currentDrawBuffer);
        miscComponents.forEach(c -> c.update(deltaT));
        // Now stuff would die etc. check the states and delete
        enemies.removeIf(actor -> actor.isDead());
        powerUps.removeIf(actor -> actor.isDead());
        bulletsInCirculation.removeIf(actor -> actor.isDead());
        // If player dies game is over reset the system
        if (player.isDead()) {
            ResetGame();
        }
        // If there are no power-ups left,
        // Player won the game!, still reset..
        if (powerUps.isEmpty()) {
            ResetGame();
        }
        // And the game goes on forever...
    }

}
