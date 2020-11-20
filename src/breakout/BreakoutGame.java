package breakout;


import comp127graphics.CanvasWindow;
import java.awt.*;


/**
 * The game of Breakout.
 *
 */

public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final Color CANVAS_COLOR = new Color(173, 216, 230);

    private CanvasWindow canvas;

    @Override
    public String toString() {
        return "BreakoutGame{" +
                "canvas=" + canvas +
                '}';
    }

    /**
     * Constructor of BreakoutGame which does not take in any parameters.
     */
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(CANVAS_COLOR);
        Paddle paddle = new Paddle(canvas.getWidth() * 0.4, canvas.getHeight() * 0.85, 100, 20);
        canvas.add(paddle);
        canvas.onMouseMove(event ->
                paddle.followMouse(event.getPosition().getX(), canvas
                ));
        Ball ball = new Ball(canvas.getWidth()* 0.45, canvas.getHeight() * 0.8, 15, 15,
                1, 1);
        canvas.add(ball);
        BrickManager brickManager = new BrickManager(canvas);
        canvas.animate(()-> {
            ball.moveBall(canvas);
            ball.paddleCollision(paddle);
            brickManager.removeBrick(ball);
            brickManager.winningLosingCondition(ball, canvas, paddle);
            }
        );

    }

    /**
     * Main method that initializes and runs the BreakoutGame.
     */

    public static void main(String[] args){
        new BreakoutGame();
    }

}
