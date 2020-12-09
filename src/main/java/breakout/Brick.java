package breakout;

import comp127graphics.Rectangle;

import java.awt.*;

/**
 * Brick class which sets up a constructor for a Brick graphics object by extending
 * the Rectangle class.
 */

public class Brick extends Rectangle {
    private static final Color BRICK_COLOR = new Color(170, 70, 200);

    /**
     * Constructor which uses super to call the constructor of Rectangle class and apply
     * parameters x,y,width,height.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(BRICK_COLOR);
    }

    @Override
    public String toString() {
        return "Brick{}";
    }

}

