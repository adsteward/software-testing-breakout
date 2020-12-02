package breakout.tests;

import breakout.Ball;
import breakout.Brick;
import breakout.BrickManager;
import breakout.Paddle;
import comp127graphics.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import java.sql.SQLOutput;
import java.util.List;

public class TestTwoHitBricks {

    CanvasWindow cw = new CanvasWindow("testWindow", 450, 800);

    // brick colors, change them if you would like
    Color red = Color.red;
    Color blue = Color.blue;

    @Test
    public void testContainsOnlyRedBlueBricks() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();
        Assert.assertTrue(bricks.stream().allMatch(
                brick -> brick.getFillColor().equals(blue) || brick.getFillColor().equals(red))
        );
    }

    @Test
    public void testSameNumberredblueBricks() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();

        int blueBricks = 0;
        int redBricks = 0;

        for (Brick brick : bricks) {
            if (brick.getFillColor().equals(red)){
                redBricks ++;
            }
            else if (brick.getFillColor().equals(blue)) {
                blueBricks++;
            }
        }

        Assert.assertEquals(blueBricks, redBricks);
    }

    @Test
    public void testRemainingHits() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();
        Assert.assertTrue(bricks.stream().allMatch(
                brick -> {
                    int hitsRemaining = brick.getHitsRemaining();

                    if (brick.getFillColor().equals(red)) {
                        return hitsRemaining == 1;
                    }
                    else if (brick.getFillColor().equals(blue)) {
                        return hitsRemaining == 2;
                    }
                    else return false;
                }
        ));
    }

    @Test
    public void testOneHitRedBrick() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();

        Brick redBrick = bricks.stream()
                .filter(brick -> brick.getFillColor().equals(red))
                .findFirst()
                .orElseThrow();

        Point brickCenter = redBrick.getCenter();
        Ball ball = new Ball(brickCenter.getX(), brickCenter.getY(), 1, 1, 0, 0);
        cw.add(ball);

        bm.removeBrick(ball);
        Assert.assertFalse(bricks.contains(redBrick));

        cw.removeAll();
    }

    @Test
    public void testOneHitBlueBrick() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();

        Brick blueBrick = bricks.stream()
                .filter(brick -> brick.getFillColor().equals(blue))
                .findFirst()
                .orElseThrow();

        Point brickCenter = blueBrick.getCenter();
        Ball ball = new Ball(brickCenter.getX(), brickCenter.getY(), 1, 1, 0, 0);
        cw.add(ball);

        bm.removeBrick(ball);
        Assert.assertTrue(bricks.contains(blueBrick));
        Assert.assertEquals(blueBrick.getFillColor(), red);

        cw.removeAll();
    }

    @Test
    public void testTwoHitsBlueBrick() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();

        Brick blueBrick = bricks.stream()
                .filter(brick -> brick.getFillColor().equals(blue))
                .findFirst()
                .orElseThrow();
        Point brickCenter = blueBrick.getCenter();

        Ball ball = new Ball(brickCenter.getX(), brickCenter.getY(), 1, 1, 0, 0);
        cw.add(ball);

        bm.removeBrick(ball);
        bm.removeBrick(ball);

        Assert.assertFalse(bricks.contains(blueBrick));

        cw.removeAll();
    }

    @Test
    public void testWin() {
        BrickManager bm = new BrickManager(cw);
        List<Brick> bricks = bm.getBricks();
        Paddle paddle = new Paddle(100, 100, 10, 10);
        cw.add(paddle);
        Ball ball = new Ball(10, 10, 10, 10, 0, 0);
        cw.add(ball);
        bricks.clear();

        GraphicsText winLose = bm.winningLosingCondition(ball, cw, paddle);
        System.out.println(bricks.size());

        Assert.assertEquals(winLose.getText(), "Congratulations! You Won! ");
    }

}
