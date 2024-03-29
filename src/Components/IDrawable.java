package Components;

import Util.AABB;

import java.awt.*;

/**
 * Drawable Interface Defines the drawable components
 * Adds an extra draw function
 */
public interface IDrawable extends IRealTimeComponent {
    void draw(Graphics2D g, AABB box);
}
