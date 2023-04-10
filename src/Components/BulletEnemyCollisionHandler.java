package Components;

import Actors.AbstractActor;
import Actors.Enemy;
import Core.GameEngine;

import java.util.ArrayList;

public class BulletEnemyCollisionHandler extends ComponentDecorator implements IRealTimeComponent, IEventComponent {
    public BulletEnemyCollisionHandler(AbstractActor wrappee) {
        super(wrappee);
    }

    @Override
    public void update(float deltaT) {
        ArrayList<Enemy> enemies = GameEngine.getEnemies();
        for (Enemy enemy : enemies) {
            if (getWrappee().collides(enemy)) {
                events.notify("Enemy");
            }
        }
    }


}
