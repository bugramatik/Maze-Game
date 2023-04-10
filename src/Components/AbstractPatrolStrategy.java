package Components;

import Actors.Enemy;
import Actors.Wall;
import Core.GameEngine;
import Util.EnumPatrolDirection;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractPatrolStrategy implements IRealTimeComponent, IPatrolStrategy, IEventListener {
    protected SpriteComponent sprite;
    protected int enemySpeed;

    public AbstractPatrolStrategy() {
        try {
            sprite = new SpriteComponent("data/img/enemy.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeDirectionIfCollides(Enemy patrol) {
        ArrayList<Wall> walls = GameEngine.getWalls();
        for (Wall wall : walls) {
            if (patrol.collides(wall)) {
                patrol.setDirection(patrol.getDirection() == EnumPatrolDirection.POSITIVE ? EnumPatrolDirection.NEGATIVE : EnumPatrolDirection.POSITIVE);
            }
            patrol.moveIfCollide(wall);
        }

    }

    @Override
    public void update(float deltaT) {}


    @Override
    public void update(float deltaT, Graphics2D g, Enemy patrol) {}

    @Override
    public void aCollisionIsHappened(String collidedObjectType) {}
}
