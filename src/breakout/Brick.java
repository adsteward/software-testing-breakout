package breakout;

import comp127graphics.Rectangle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Brick class which sets up a constructor for a Brick graphics object by extending
 * the Rectangle class.
 */

public class Brick extends Rectangle {
    private static final Color BRICK_COLOR = new Color(170, 70, 200);
    private int hitsRemaining;
    private final Map<Integer, Color> brickCols = Map.of(1, Color.red, 2, Color.blue);

    /**
     * Constructor which uses super to call the constructor of Rectangle class and apply
     * parameters x,y,width,height.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Brick(double x, double y, double width, double height, int hitsNeeded) {
        super(x, y, width, height);
        this.hitsRemaining = hitsNeeded;
        this.setFillColor(brickCols.get(hitsNeeded));
    }

    public int getHitsRemaining() {
        return hitsRemaining;
    }

    public void hit() {
        hitsRemaining--;
        if (hitsRemaining > 0) {
            setFillColor(brickCols.get(hitsRemaining));
        }
    }

    @Override
    public String toString() {
        return "Brick{}";
    }

}

