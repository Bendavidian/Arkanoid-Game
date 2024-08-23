// Ben Davidian - 206844045

package GameAnimations;

import Backgrounds.Night;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import intefaces.Animation;
import listeners.Counter;

import java.awt.Color;

/**
 * Represents the end screen animation that displays the result and the score.
 */
public class EndScreen implements Animation {
    public static final int TWO_TIMES = 2;
    public static final int QUARTER = 4;
    public static final int THIRD = 3;
    public static final int TEN = 10;
    public static final int EIGHT = 8;
    public static final int SEVEN = 7;
    public static final int THIRTY_FIVE = 30;
    public static final int FIVE = 5;
    public static final int FIFTEEN = 15;
    public static final int SEVENTY_FIVE = 75;
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;
    private String result;
    private Counter score;
    public static final int SIZE_SCORE = 35;
    public static final int SIZE_RESULT_STRING = 55;
    public static final int FIFTH = FIVE;
    public static final int TEN_TIMES = SEVEN;
    public static final int BLACK_BEHIND  = THIRD;
    public static final int FACE_RADIUS = 120;
    private static final Color WIN_BACKGROUND_COLOR = new Color(31, 69, 87);
    private static final Color LOSE_BACKGROUND_COLOR = new Color(199, 123, 47);

    /**
     * Constructs the EndScreen animation with the given result and score.
     * @param result the result of the game ("Game Over." or "You Win!")
     * @param score  the player's score
     */
    public EndScreen(String result, Counter score) {
        this.result = result;
        this.score = score;
    }
    /**
     * Performs one frame of the animation.
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Set the color for background
        Color backgroundColor = result.equals("Game Over.") ? LOSE_BACKGROUND_COLOR : WIN_BACKGROUND_COLOR;
        Point p = new Point(0, HEIGHT);
        Night night = new Night(new Rectangle(p, WIDTH, HEIGHT), backgroundColor);
        night.drawOn(d);
        // Set the color for text
        Color textColor = result.equals("Game Over.") ? Color.RED : Color.GREEN;
        // Display the message on the screen
        int textX = d.getWidth() / THIRD + TEN_TIMES * TWO_TIMES;
        int textY = d.getHeight() / FIFTH;
        d.setColor(Color.BLACK);
        d.drawText(textX - BLACK_BEHIND, textY - BLACK_BEHIND, result, SIZE_RESULT_STRING);
        d.setColor(textColor);
        d.drawText(textX - TWO_TIMES, textY - TWO_TIMES, result, SIZE_RESULT_STRING);
        d.setColor(Color.BLACK);
        d.drawText(textX, textY, result, SIZE_RESULT_STRING);

        int scoreX = d.getWidth() / QUARTER + TEN_TIMES * THIRD;
        int scoreY = d.getHeight() * TWO_TIMES / FIFTH;
        d.setColor(Color.BLACK);
        d.drawText(scoreX - BLACK_BEHIND, scoreY - BLACK_BEHIND,
                " Your Score is   " + score.getValue(), SIZE_SCORE);
        d.setColor(textColor);
        d.drawText(scoreX - TWO_TIMES, scoreY - TWO_TIMES,
                " Your Score is   " + score.getValue(), SIZE_SCORE);
        d.setColor(Color.BLACK);
        d.drawText(scoreX, scoreY, " Your Score is   " + score.getValue(), SIZE_SCORE);
        // Set the color for the smiley face
        Color faceColor = result.equals("Game Over.") ? Color.RED : Color.GREEN;
        d.setColor(faceColor);

        // Draw the face
        int faceX = (d.getWidth() - FACE_RADIUS) / TWO_TIMES;
        int faceY = (d.getHeight() - FACE_RADIUS) / TWO_TIMES + FIFTEEN;
        d.fillOval(faceX, faceY, FACE_RADIUS, FACE_RADIUS);

        // Draw the eyes
        int eyeX1 = faceX + SEVENTY_FIVE;
        int eyeX2 = faceX + THIRTY_FIVE;
        int eyeY = faceY + FACE_RADIUS / QUARTER + SEVEN;
        int eyeWidth = FACE_RADIUS / EIGHT;
        int eyeHeight = FACE_RADIUS / EIGHT;
        d.setColor(Color.WHITE);
        d.fillOval(eyeX1, eyeY, eyeWidth, eyeHeight);
        d.fillOval(eyeX2, eyeY, eyeWidth, eyeHeight);

        // Draw the mouth
        int mouthX = faceX + FACE_RADIUS / TWO_TIMES - SEVEN;
        int mouthY;
        int mouthWidth = FACE_RADIUS / FIVE;
        int mouthHeight = FACE_RADIUS / FIVE;

        if (result.equals("Game Over.")) {
            // Draw a sad face with three downward lines
            mouthY = faceY + TWO_TIMES * FACE_RADIUS / THIRD + mouthHeight / TWO_TIMES - TEN;
            mouthX -= QUARTER;
            d.setColor(Color.BLACK);
            d.drawLine(mouthX, mouthY, mouthX + mouthWidth, mouthY);
            d.drawLine(mouthX, mouthY, mouthX - mouthWidth / TWO_TIMES, mouthY + mouthHeight);
            d.drawLine(mouthX + mouthWidth, mouthY, mouthX + THIRD * mouthWidth / TWO_TIMES,
                    mouthY + mouthHeight);
        } else {
            // Draw a happy face with three upward lines
            mouthY = faceY + TWO_TIMES * FACE_RADIUS / THIRD + mouthHeight;
            d.setColor(Color.BLACK);
            d.drawLine(mouthX - TEN, mouthY, mouthX + mouthWidth + THIRD, mouthY);
            d.drawLine(mouthX - TEN, mouthY, mouthX - mouthWidth, mouthY - mouthHeight);
            d.drawLine(mouthX + mouthWidth / TWO_TIMES + mouthWidth, mouthY - mouthHeight,
                    mouthX + mouthWidth + TWO_TIMES, mouthY);
        }
        // draws a message to press space key
        int pressX = d.getWidth() / QUARTER + TEN_TIMES * THIRD;
        int pressY = d.getHeight() * TWO_TIMES / THIRD;
        d.setColor(Color.BLACK);
        d.drawText(pressX, pressY, " Press space to continue ", SIZE_SCORE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
