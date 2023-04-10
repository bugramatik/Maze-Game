package Actors;

import Components.SpriteComponent;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;

public class Wall extends AbstractActor {
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    private SpriteComponent sprite;

    public Wall(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent("data/img/wall.png");
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
        return false;
    }
}
