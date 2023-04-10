package Actors;

import Components.IEventListener;
import Components.IPatrolStrategy;
import Util.EnumPatrolDirection;
import Util.Position2D;

import java.awt.*;

public class Enemy extends AbstractActor implements IEventListener {
    /**
     * Constructor, directly sets the every parameter
     *
     * @param pos "top right" (wrt. the screen coordinates) of the box
     * @param szX horizontal size of the box in pixels
     * @param szY vertical size of the box in pixels
     */
    private final IPatrolStrategy strategy;
    private int enemySpeed;
    private int life;
    private EnumPatrolDirection direction;


    public Enemy(Position2D<Float> pos, float szX, float szY, IPatrolStrategy strategy) {
        super(pos, szX, szY);
        this.strategy = strategy;
        this.direction = EnumPatrolDirection.POSITIVE;
        life = 60;
        setType("Enemy");

    }

    @Override
    public void aCollisionIsHappened(String collidedObjectType) {
        this.strategy.changeDirection(this);
    }


    @Override
    public boolean isDead() {
        return life <= 0;
    }

    @Override
    public void update(float deltaT, Graphics2D g) {
        this.strategy.update(deltaT, g, this);
    }

    public EnumPatrolDirection getDirection() {
        return direction;
    }

    public void setDirection(EnumPatrolDirection direction) {
        this.direction = direction;
    }

    public void hit() {
        life--;
    }
}
