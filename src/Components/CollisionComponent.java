package Components;

import Actors.AbstractActor;
import Actors.Enemy;
import Actors.PowerUp;
import Actors.Wall;
import Core.GameEngine;

import java.util.ArrayList;

public class CollisionComponent extends ComponentDecorator implements IRealTimeComponent, IEventComponent {


    public CollisionComponent(AbstractActor wrappee) {
        super(wrappee);
    }

    @Override
    public void update(float deltaT) {
        ArrayList<Wall> walls = GameEngine.getWalls();
        for (Wall wall : walls) {
            if (getWrappee().collides(wall)) {
                events.notify("Wall");
            }
        }

        ArrayList<PowerUp> powerUps = GameEngine.getPowerUps();
        for (PowerUp powerUp : powerUps) {
            if (getWrappee().collides(powerUp)) {
                events.notify("PowerUp");
            }
        }

        if (!(getWrappee().getType() == "Enemy")) {
            ArrayList<Enemy> enemies = GameEngine.getEnemies();
            for (Enemy enemy : enemies) {
                if (getWrappee().collides(enemy)) {
                    events.notify("Enemy");
                }
            }
        }


    }


}
