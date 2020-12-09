package breakout;

import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;


import java.awt.*;

/**
 * Paddle class which extends a rectangle class and creates a paddle.
 */
public class Paddle extends Rectangle {

    private static final Color PADDLE_COLOR = new Color(150, 200, 20);

    /**
     * Constructor which uses super to call the rectangle constructor and apply the
     * parameters x, y, width, height. It also sets the color of the paddle.
     * @param x
     * @param y
     * @param width
     * @param height
     */

    public Paddle(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(PADDLE_COLOR);
    }
    /**
     * Checks for canvas boundaries to make sure paddle does not go out of the canvas
     * frame and also, ensures that the paddle movement follows the mouse.
     */
    public void followMouse(double mouseX, CanvasWindow canvas) {
        this.setPosition(mouseX - (this.getWidth()/2), this.getY());
        if (this.getX() < 5) {
            this.setPosition(2, this.getY());
        }
        if (this.getX() + this.getWidth() > canvas.getWidth()) {
            this.setPosition(canvas.getWidth() - (this.getWidth() + 5), this.getY());
        }
    }

    @Override
    public String toString() {
        return "Paddle{}";
    }
}



