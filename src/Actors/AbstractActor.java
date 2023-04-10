package Actors;

import Components.IRealTimeComponent;
import Util.AABB;
import Util.Position2D;

import java.awt.*;

// Meta Actor Class
// Everything in the game is an actor
public abstract class AbstractActor extends AABB implements IRealTimeComponent {

    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    private String type;

    public AbstractActor(Position2D<Float> pos, float szX, float szY) {
        super(pos, szX, szY);
    }


    public void update(float deltaT) {
    }


    public void update(float deltaT, Graphics2D g) {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract boolean isDead();
}
