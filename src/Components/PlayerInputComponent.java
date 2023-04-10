package Components;

import Actors.Bullet;
import Actors.Player;
import Core.GameEngine;
import Util.EnumDirection;
import Util.Position2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputComponent extends ComponentDecorator implements IRealTimeComponent, KeyListener {
    // Internal States
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean firePressed;


    public PlayerInputComponent(Player wrappee) {
        super(wrappee);
    }

    @Override
    public void update(float deltaT) {
        Player wrappedPlayer = (Player) getWrappee();
        wrappedPlayer.setDirection(leftPressed ? EnumDirection.LEFT : wrappedPlayer.getDirection());
        wrappedPlayer.setDirection(rightPressed ? EnumDirection.RIGHT : wrappedPlayer.getDirection());
        wrappedPlayer.setDirection(upPressed ? EnumDirection.UP : wrappedPlayer.getDirection());
        wrappedPlayer.setDirection(downPressed ? EnumDirection.DOWN : wrappedPlayer.getDirection());
        if (!(downPressed || upPressed || rightPressed || leftPressed)) {
            wrappedPlayer.setDirection(EnumDirection.STATIONARY);
        }
        if (firePressed) {
            Position2D<Float> initialBulletPos = new Position2D<>(GameEngine.getPlayer().getPos().x, GameEngine.getPlayer().getPos().y);
            Bullet bullet = new Bullet(initialBulletPos, GameEngine.getPlayer().getSizeX(), GameEngine.getPlayer().getSizeY(), GameEngine.getPlayer().getDirection());
            GameEngine.createBullet(bullet);
            firePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) firePressed = true;
    }

}
