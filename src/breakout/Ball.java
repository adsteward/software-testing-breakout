package breakout;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

/**
 * Creates a Ball class which extends the Ellipse class.
 */
public class Ball extends Ellipse {

    private static final double BALL_RADIUS = 3;
    private double velocityDx;
    private double velocityDy;
    private int turnsLeft = 3;
    private static final Color BALL_COLOR = new Color(255, 160, 50);

    /**
     * Ball constructor which uses super to call the Ellipse constructor and apply its
     * parameters x, y, height, width, velocityDx, velocityDy.
     * @param x
     * @param y
     * @param height
     * @param width
     * @param velocityDx
     * @param velocityDy
     */
    public Ball(double x, double y, double height, double width,
                double velocityDx, double velocityDy) {
        super(x - BALL_RADIUS, y - BALL_RADIUS, height * 1.5, width * 1.5);
        this.velocityDx = velocityDx;
        this.velocityDy = velocityDy;
        this.setFillColor(BALL_COLOR);
    }

    /**
     * Sets the position for the ball and checks for canvas walls to make sure the ball
     * does not go out of the bounds.
     * @param canvas
     */
    public void moveBall(CanvasWindow canvas)  {
        if(this.getX() > canvas.getWidth() - this.getWidth()|| this.getX() < 0) {
            velocityDx = -velocityDx;
        }
        if(this.getY() > canvas.getHeight() - this.getHeight()|| this.getY() < 0) {
            velocityDy = -velocityDy;
        }
        this.setPosition(this.getX() + velocityDx, this.getY() + velocityDy);
        }

    /**
     * Checks for the collision of the ball with a paddle and if true, switches the Dy
     * velocity of the ball.
     * @param paddle
     */
    public void paddleCollision(Paddle paddle)    {
        if(this.getBounds().intersects(paddle.getBounds())) {
            velocityDy=-Math.abs(velocityDy);
        }
    }

    public void changeVelocityDy()    {
        velocityDy = -velocityDy;
    }
    public void stopBall()  {
        velocityDy = 0;
        velocityDx = 0;
    }

    /**
     * Takes in paddle and canvas as parameters, checks if a player has missed the ball
     * and if so, pauses the game for 2 second and decrements the turnsLeft (player lost
     * 1 life).
     * @param paddle
     * @param canvas
     * @return int turnsLeft
     */
    public int checkForLose(Paddle paddle, CanvasWindow canvas)  {
        if(this.getY()>paddle.getY()+paddle.getHeight() + 50)    {
            canvas.pause(2000);
            this.setPosition(250, 300);
            turnsLeft--;
        }
        return turnsLeft;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "velocityDx=" + velocityDx +
                ", velocityDy=" + velocityDy +
                ", turnsLeft=" + turnsLeft +
                '}';
    }
}




