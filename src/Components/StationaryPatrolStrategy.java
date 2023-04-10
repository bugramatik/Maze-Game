package Components;

import Actors.Enemy;

import java.awt.*;

public class StationaryPatrolStrategy extends AbstractPatrolStrategy {

    @Override
    public void update(float deltaT, Graphics2D g, Enemy patrol) {
        sprite.draw(g, patrol);

    }

    @Override
    public void changeDirection(Enemy patrol) {

    }


}
