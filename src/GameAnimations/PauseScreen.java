// Ben Davidian

package GameAnimations;

import Backgrounds.Night;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import intefaces.Animation;

import java.awt.Color;

/**
 * The PauseScreen class represents a screen that is displayed when the game is paused.
 * It implements the Animation interface, allowing it to be used as an animation in the game.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private static final String RESUME_TEXT = "paused -- press space to continue";
    private static final Color BACKGROUND_COLOR = new Color(141, 134, 117);

    /**
     * Constructs a new PauseScreen.
     * @param keyboard The keyboard sensor used to detect key presses.
     */
    public PauseScreen(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.stop = false;
    }

    /**
     * Performs one frame of the pause screen animation.
     * @param d The drawing surface on which to draw the frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Point p = new Point(0, 620);
        Night night = new Night(new Rectangle(p, 800, 600), BACKGROUND_COLOR);
        night.drawOn(d);
        d.drawText(120, d.getHeight() / 3, RESUME_TEXT, 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
        // Draw the stop symbol
        int centerX = d.getWidth() / 2;
        int centerY = d.getHeight() - 200;
        int circleRadius = 90;
        int gap = 15;
        int rectangleWidth = 100;
        int rectangleHeight = 20;

        // Draw the blue outer circle
        d.setColor(Color.BLUE);
        d.drawCircle(centerX, centerY, circleRadius);
        d.fillCircle(centerX, centerY, circleRadius);
        // Draw the cyan inner circle
        d.setColor(Color.CYAN);
        d.drawCircle(centerX, centerY, circleRadius - (gap / 2));
        d.fillCircle(centerX, centerY, circleRadius - (gap / 2));
        // Draw the black inner circle
        d.setColor(Color.BLACK);
        d.drawCircle(centerX, centerY, circleRadius - gap);
        d.fillCircle(centerX, centerY, circleRadius - gap);

        // Draw the horizontal rectangles
        d.setColor(Color.ORANGE);
        int rectangleX = centerX - rectangleHeight / 2;
        d.fillRectangle(rectangleX - 20, centerY - rectangleWidth / 2, rectangleHeight, rectangleWidth);
        d.fillRectangle(rectangleX + 20, centerY - rectangleWidth / 2, rectangleHeight, rectangleWidth);
    }

    /**
     * Checks if the pause screen animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
