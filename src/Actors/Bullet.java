package Actors;

import Components.IEventListener;
import Components.SpriteComponent;
import Core.GameEngine;
import Util.EnumDirection;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class Bullet extends AbstractActor implements IEventListener {
    SpriteComponent sprite;
    boolean alive;
    EnumDirection direction;

    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Bullet(Position2D<Float> pos, float szX, float szY, EnumDirection direction) {
        super(pos, szX, szY);
        setType("Bullet");
        alive = true;
        this.direction = direction != EnumDirection.STATIONARY ? direction : EnumDirection.RIGHT;
        this.shrink(0.3f);
        try {
            sprite = new SpriteComponent("data/img/bullet.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(float deltaT, Graphics2D g) {
        this.getPos().y = this.getPos().y + (direction == EnumDirection.UP ? -1 : 0) * deltaT * 300;
        this.getPos().y = this.getPos().y + (direction == EnumDirection.DOWN ? 1 : 0) * deltaT * 300;
        this.getPos().x = this.getPos().x + (direction == EnumDirection.RIGHT ? 1 : 0) * deltaT * 300;
        this.getPos().x = this.getPos().x + (direction == EnumDirection.LEFT ? -1 : 0) * deltaT * 300;

        GameEngine.getWalls().forEach(wall -> {
            if (wall.collides(this)) {
                alive = false;
            }
        });
        GameEngine.getEnemies().forEach(enemy -> {
            if (enemy.collides(this)) {
                enemy.hit();
            }
        });
        EnumDirection playersDirection = GameEngine.getPlayer().getDirection();
        sprite.draw(g, this);

    }

    @Override
    public boolean isDead() {
        return !alive;
    }

    @Override
    public void aCollisionIsHappened(String collidedObjectType) {
        switch (collidedObjectType) {
            case "Wall":
                alive = false;
                break;
            case "Enemy":
                GameEngine.getEnemies().forEach(enemy -> {
                    if (enemy.collides(this)) {
                        enemy.hit();
                    }
                });
                break;
        }
    }

    public void setDirection(EnumDirection direction) {
        this.direction = direction;
    }
}
