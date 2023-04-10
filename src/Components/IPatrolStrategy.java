package Components;

import Actors.Enemy;

import java.awt.*;

public interface IPatrolStrategy {

    public void update(float deltaT, Graphics2D g, Enemy patrol);

    public void changeDirection(Enemy patrol);

}
