// Ben Davidian

package Levels;

import Backgrounds.Target;
import collisions.cObject.Block;
import geometry.Point;
import geometry.Rectangle;
import intefaces.LevelInformation;
import sprites.Velocity;
import intefaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the "Direct Hit" level.
 * Implements the LevelInformation interface and provides specific values for this level.
 */
public class DirectHit implements LevelInformation {
    public static final int NUM_OF_BALLS = 1;
    public static final int NUM_OF_BLOCKS = 1;
    public static final int VELOCITY_ANGLE = 1;
    public static final int VELOCITY_SPEED = 5;
    public static final int SPEED_OF_PADDLE = 4;
    public static final int WIDTH_OF_PADDLE = 70;
    private static final int WIDTH_OF_FRAME = 830;
    private static final int HEIGHT_OF_FRAME = 620;
    private static final String LEVEL_NAME = "Direct Hit";

    /**
     * Returns the number of balls in the level.
     * @return The number of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    /**
     * Returns a list of initial velocities for the balls.
     * @return The list of initial velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(VELOCITY_ANGLE, VELOCITY_SPEED));
        return velocities;
    }
    /**
     * Returns the speed of the paddle.
     * @return The speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return SPEED_OF_PADDLE;
    }
    /**
     * Returns the width of the paddle.
     * @return The width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return WIDTH_OF_PADDLE;
    }
    /**
     * Returns the name of the level.
     * @return The level name.
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }
    /**
     * Returns the background sprite of the level.
     * @return The background sprite.
     */
    @Override
    public Sprite getBackground() {
        Point p = new Point(0, HEIGHT_OF_FRAME);
        return new Target(new Rectangle(p, WIDTH_OF_FRAME, HEIGHT_OF_FRAME), Color.black);
    }
    /**
     * Returns a list of blocks in the level.
     * @return The list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p = new Point(390, 192);
        blocks.add(new Block(new Rectangle(p, 20, 20), Color.RED));
        return blocks;
    }
    /**
     * Returns the number of blocks that should be removed in order to consider the level.
     * @return The number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
