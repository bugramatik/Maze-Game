package Components;

import Actors.Enemy;
import Util.EnumPatrolDirection;

import java.awt.*;

public class VerticalPatrolStrategy extends AbstractPatrolStrategy {


    @Override
    public void update(float deltaT, Graphics2D g, Enemy patrol) {
        patrol.getPos().y = patrol.getPos().y + (patrol.getDirection() == EnumPatrolDirection.POSITIVE ? 1 : -1) * deltaT * 120;
        sprite.draw(g, patrol);
    }

    @Override
    public void changeDirection(Enemy patrol) {
        changeDirectionIfCollides(patrol);
    }


}
