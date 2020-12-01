package breakout;

import comp127graphics.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a wall of Bricks using the Brick class and displays it to canvas.
 */

public class BrickManager {
    private GraphicsGroup brickGroup;
    private List<Brick> bricks = new ArrayList<>();

    /**
     * Constructor which creates a brickGroup graphicsGroup, calls createBricks method
     * to build a wall and adds it to canvas.
     * @param canvas
     */
    public BrickManager(CanvasWindow canvas) {


        brickGroup = new GraphicsGroup();
        createBricks(canvas);
        canvas.add(brickGroup);
    }

    /**
     * Takes in canvas as a parameter and creates a wall of bricks.
     * @param canvas
     */
    private void createBricks(CanvasWindow canvas) {
        double margin = canvas.getWidth() * 0.05;
        double spacing = canvas.getWidth() * 0.01;
        double y = canvas.getWidth() * 0.15;
        double x = margin;
        double length = 0;
        int hitsNeeded;
        for (int i = 0; i < 24; i++) {

            hitsNeeded = (i >= 12)  ? 2 : 1;

            Brick brick = new Brick(x, y, canvas.getWidth() * 0.1,
                    canvas.getWidth() * 0.03, hitsNeeded);
            length = length + brick.getWidth() + spacing;
            if (length < canvas.getWidth() - 4 * margin) {
                x = x + brick.getWidth() + spacing;
            } else {
                x = margin;
                length = 0;
                y = y + spacing + brick.getHeight();

            }
            brickGroup.add(brick);
            bricks.add(brick);
        }
    }

    /**
     * Takes in a ball from Ball class as a parameter and checks for intersection
     * between a ball and any of the bricks from the bricks List and brickGroup
     * GraphicsGroup. If intersection happens, removes the brick from both list and
     * the graphics group and changes Dy velocity of the ball.
     * @param ball
     */
    public void removeBrick(Ball ball) {
        for (int i = 0; i < bricks.size(); i++) {
            if (ball.getBounds().intersects(bricks.get(i).getBounds())) {

                Brick hitBrick = bricks.get(i);
                hitBrick.hit();
                if (hitBrick.getHitsRemaining() == 0) {
                    brickGroup.remove(hitBrick);
                    bricks.remove(hitBrick);
                }
                ball.changeVelocityDy();
            }
        }
    }

    /**
     * Sets conditions for winning and loosing the Breakout game. If there are no bricks
     * left in the bricks list, the ball stops and a winning message is displayed on canvas.
     * If the user misses the ball 3 times, they run out of lives and a losing message is
     * displayed on canvas after checking the turnsLeft variable by calling checkForLose
     * method from the Ball class.
     * @param ball
     * @param canvas
     * @param paddle
     */

    public void winningLosingCondition(Ball ball, CanvasWindow canvas, Paddle paddle) {
        if (bricks.size() == 0) {
            canvas.animate(ball::stopBall);
            GraphicsText victoryMessage = new GraphicsText("Congratulations! You Won! ");
            victoryMessage.setFontSize(20);
            canvas.add(victoryMessage);
            victoryMessage.setCenter(300, 400);
        }
        if (ball.checkForLose(paddle, canvas) == 0) {
            canvas.animate(ball::stopBall);
            GraphicsText loseMessage = new GraphicsText("Unfortunately, you lost. " +
                    "Try again!");
            loseMessage.setFontSize(20);
            canvas.add(loseMessage);
            loseMessage.setCenter(300, 400);

        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public String toString() {
        return "BrickManager{" +
                "brickGroup=" + brickGroup +
                ", bricks=" + bricks +
                '}';
    }
}


