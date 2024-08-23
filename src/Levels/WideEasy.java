// Ben Davidian - 206844045

package Levels;

import Backgrounds.Sun;
import collisions.cObject.Block;
import geometry.Point;
import geometry.Rectangle;
import intefaces.LevelInformation;
import sprites.Velocity;
import intefaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The "WideEasy" class represents a specific level in the game.
 * It implements the LevelInformation interface and provides information.
 * This level features a wide and easy configuration with a sun-themed background and various colored blocks.
 */
public class WideEasy implements LevelInformation {
    private static final int WIDTH_OF_FRAME = 830;
    private static final int HEIGHT_OF_FRAME = 620;
    public static final int NUM_OF_BALLS = 10;
    public static final int NUM_OF_BLOCKS = 15;
    public static final int WIDTH_OF_PADDLE = 560;
    public static final int SPEED_OF_PADDLE = 3;
    public static final int BALLS_GAP = 10;
    public static final int BALLS_SPEED = 5;
    public static final int FIRST_ANGLE_LEFT = 310;
    public static final int FIRST_ANGLE_RIGHT = 10;
    private static final String LEVEL_NAME = "Wide Easy";

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
        ArrayList<Velocity> velocities = new ArrayList<>();
        int angleLeft = FIRST_ANGLE_LEFT;
        int angleRight = FIRST_ANGLE_RIGHT;
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            angleLeft +=  BALLS_GAP;
            velocities.add(Velocity.fromAngleAndSpeed(angleLeft, BALLS_SPEED));
        }
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            angleRight += BALLS_GAP;
            velocities.add(Velocity.fromAngleAndSpeed(angleRight, BALLS_SPEED));
        }
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
     * Returns the background sprite of the level.
     * @return The background sprite.
     */

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, HEIGHT_OF_FRAME);
        return new Sun(new Rectangle(p, WIDTH_OF_FRAME, HEIGHT_OF_FRAME), Color.WHITE);
    }
    /**
     * Returns a list of blocks in the level.
     * @return The list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int leftSide = 21;
        int blockWidth = 51;
        // define the mapping between block indices and colors
        Map<Integer, Color> colorMap = new HashMap<>();
        colorMap.put(0, Color.RED);
        colorMap.put(1, Color.RED);
        colorMap.put(2, Color.ORANGE);
        colorMap.put(3, Color.ORANGE);
        colorMap.put(4, Color.YELLOW);
        colorMap.put(5, Color.YELLOW);
        colorMap.put(6, Color.GREEN);
        colorMap.put(7, Color.GREEN);
        colorMap.put(8, Color.GREEN);
        colorMap.put(9, Color.BLUE);
        colorMap.put(10, Color.BLUE);
        colorMap.put(11, Color.PINK);
        colorMap.put(12, Color.PINK);
        colorMap.put(13, Color.CYAN);
        colorMap.put(14, Color.CYAN);
        // generate blocks based on the defined mapping
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            if (i == 6) {
                blockWidth -= 5;
            } else if (i == 7) {
                blockWidth += 5;
            }

            Rectangle rectangle = new Rectangle(new Point(leftSide, 260), blockWidth, 28);
            Color color = colorMap.get(i);
            // create a new Block with the corresponding color
            blocks.add(new Block(rectangle, color));
            leftSide += blockWidth;
        }
        return blocks;
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
     * Returns the number of blocks that should be removed in order to consider the level.
     * @return The number of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
