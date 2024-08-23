// Ben Davidian - 206844045

package Levels;

import Backgrounds.Building;
import collisions.cObject.Block;
import geometry.Point;
import geometry.Rectangle;
import intefaces.LevelInformation;
import sprites.Velocity;
import intefaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The "GreenAndBuilding" class represents a specific level in the game.
 * It implements the LevelInformation interface
 */
public class GreenAndBuilding implements LevelInformation {
    private static final String LEVEL_NAME = "Green & Building";
    public static final int NUM_OF_BALLS = 2;
    public static final int SPEED_OF_PADDLE = 6;
    public static final int SPEED_OF_BALL = 6;
    public static final int WIDTH_OF_PADDLE = 140;
    private static final int NUM_OF_BLOCKS = 51;
    private static final int WIDTH_OF_FRAME = 830;
    private static final int HEIGHT_OF_FRAME = 620;
    private static final int FIRST_ANGLE = 55;
    private static final int SECOND_ANGLE = 315;
    private static final Color GREEN = new Color(2, 141, 14);

    /**
     * Returns the number of balls in the level.
     * @return The number of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * Returns the speed of the paddle.
     * @return The speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return SPEED_OF_PADDLE;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(FIRST_ANGLE, SPEED_OF_BALL));
        velocities.add(Velocity.fromAngleAndSpeed(SECOND_ANGLE, SPEED_OF_BALL));
        return velocities;
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
        return new Building(new Rectangle(p, WIDTH_OF_FRAME, HEIGHT_OF_FRAME), GREEN);
    }
    /**
     * Returns a list of blocks in the level.
     * @return The list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // Create a new instance of Random
        Random rand = new Random();
        // Create the blocks and add them to the game
        for (int i = 1; i <= SPEED_OF_PADDLE; i++) {
            for (int j = 1; j < 13 - i; j++) {
                if (i == 2 && j == 3) {
                    // Add the "killing block"
                    Rectangle killingBlockRect = new Rectangle(new Point(780 - 50 * j, 100 + 20 * i),
                            50, 20);
                    Block killingBlock = new Block(killingBlockRect, Color.CYAN);
                    blocks.add(killingBlock);
                } else if (i == 3 && j == 9) {
                    // Add the block that introduces a new ball
                    Rectangle ballBlockRect = new Rectangle(new Point(780 - 50 * j, 100 + 20 * i),
                            50, 20);
                    Block ballBlock = new Block(ballBlockRect, Color.WHITE);
                    blocks.add(ballBlock);
                } else {
                    Rectangle rect = new Rectangle(new Point(780 - 50 * j, 100 + 20 * i), 50, 20);
                    // Generate random values for Hue, Saturation and Brightness
                    float hue = rand.nextFloat();
                    float saturation = 0.5f + rand.nextFloat() * 0.5f;
                    float brightness = 0.5f + rand.nextFloat() * 0.5f;
                    // Use the generated values to create a random color
                    Color randomColor = Color.getHSBColor(hue, saturation, brightness);
                    blocks.add(new Block(rect, randomColor));
                }
            }
        }
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
