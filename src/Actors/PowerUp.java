package Actors;

import Components.IEventListener;
import Components.SpriteComponent;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class PowerUp extends AbstractActor implements IEventListener {
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    SpriteComponent sprite;
    boolean alive;

    public PowerUp(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        setType("PowerUp");
        alive = true;

        try {
            sprite = new SpriteComponent("data/img/scroll.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(float deltaT, Graphics2D g) {
        sprite.draw(g, this);
    }

    @Override
    public boolean isDead() {
        return !alive;
    }

    public void eatPowerUp() {
        alive = false;
    }

    @Override
    public void aCollisionIsHappened(String collidedObjectType) {

    }
}
