package test;

import breakout.Ball;
import breakout.Brick;
import breakout.BrickManager;
import comp127graphics.CanvasWindow;
import org.junit.Assert;
import org.junit.Test;

public class BrickTest {
    @Test
    public void oneHitBrick() throws Exception{
        // tests if a brick that should be removed after one hit is

        // add param to Brick for "shots to break", here set to one
        // change color of brick based on this stb variable
        Brick brick = new Brick(1,2,10,10, 1);
        Ball ball = new Ball(1,2, 10, 10, 0 ,0);
        CanvasWindow c = new CanvasWindow("win", 100, 100);
        BrickManager manager = new BrickManager(c);

        c.add(ball);
        manager.brickGroup.add(brick);
        manager.bricks.add(brick);
        manager.removeBrick(ball);
        c.remove(ball);

        Assert.assertNull(c.getElementAt(1,2));
        c.removeAll();
    }

    @Test
    public void twoHitBrickHitOnce() throws Exception{
        // tests if a brick that should be removed after two hit is still there after one

        // add param to Brick for "shots to break", here set to two
        // change color of brick based on this stb variable
        Brick brick = new Brick(1,2,10,10, 2);
        Ball ball = new Ball(1,2, 10, 10, 0 ,0);
        CanvasWindow c = new CanvasWindow("win", 100, 100);
        BrickManager manager = new BrickManager(c);

        c.add(ball);
        manager.brickGroup.add(brick);
        manager.bricks.add(brick);
        manager.removeBrick(ball);
        c.remove(ball);

        Assert.assertEquals(c.getElementAt(1,2), brick);
    }

    @Test
    public void twoHitBrickHitTwice() throws Exception{
        // tests if a brick that should be removed after two hit is removed after two

        // add param to Brick for "shots to break", here set to two
        // change color of brick based on this stb variable
        Brick brick = new Brick(1,2,10,10, 2);
        Ball ball = new Ball(1,2, 10, 10, 0 ,0);
        CanvasWindow c = new CanvasWindow("win", 100, 100);
        BrickManager manager = new BrickManager(c);

        c.add(ball);
        manager.brickGroup.add(brick);
        manager.bricks.add(brick);
        manager.removeBrick(ball);
        manager.removeBrick(ball);
        c.remove(ball);

        Assert.assertNull(c.getElementAt(1,2));
    }
}
