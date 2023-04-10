package Actors;

import Components.IEventListener;
import Components.SpriteComponent;
import Core.GameEngine;
import Util.EnumDirection;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class Player extends AbstractActor implements IEventListener {
    private boolean alive;
    private EnumDirection direction;
    private SpriteComponent sprite;

    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    public Player(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        this.alive = true;
        setType("Player");


        try {
            sprite = new SpriteComponent("data/img/player.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EnumDirection getDirection() {
        return direction;
    }

    public void setDirection(EnumDirection direction) {
        this.direction = direction;
    }

    @Override
    public void update(float deltaT, Graphics2D g) {

        this.getPos().y = this.getPos().y + (this.getDirection() == EnumDirection.UP ? -1 : 0) * deltaT * 110;
        this.getPos().y = this.getPos().y + (this.getDirection() == EnumDirection.DOWN ? 1 : 0) * deltaT * 110;
        this.getPos().x = this.getPos().x + (this.getDirection() == EnumDirection.RIGHT ? 1 : 0) * deltaT * 110;
        this.getPos().x = this.getPos().x + (this.getDirection() == EnumDirection.LEFT ? -1 : 0) * deltaT * 110;
        sprite.draw(g, this);
    }

    @Override
    public boolean isDead() {
        return !alive;
    }

    @Override
    public void aCollisionIsHappened(String collidedObjectType) {
        switch (collidedObjectType) {
            case "Enemy":
                alive = false;
                break;
            case "Wall":
                GameEngine.getWalls().forEach(this::moveIfCollide);
                break;
            case "PowerUp":

                for (PowerUp powerUp : GameEngine.getPowerUps()) {
                    if (this.collides(powerUp)) {
                        powerUp.eatPowerUp();
                    }
                }
                break;
        }
    }


    public void kill() {
        alive = true;
    }
}
