// Ben Davidian

package collisions.cObject;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import intefaces.Collidable;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import intefaces.Sprite;
import sprites.Velocity;
import sprites.sobject.Ball;

import java.awt.Color;

/**
 * The Paddle class represents a paddle in a game of Breakout.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private Color color;
    private GameEnvironment game;
    private static final double MAX_SIDE_GAP = 22;
    private static final double REGIONS = 5;
    private static final double SPEED = 13;
    private static final int WIDTH = 800;
    private static final double EPSILON = 0.0001;
    private static final double THREE_HUNDRED_DEGREES = 300;
    private static final double THREE_THIRTY_DEGREES = 330;
    private static final double THIRTY_DEGREES = 30;
    private static final double SIXTY_DEGREES = 60;
    private static final double TWO_PARTS = 2;
    private static final double THREE_PARTS = 3;
    private static final double FOUR_PARTS = 4;

    /**
     * Constructs a new collisions.object.Paddle.
     * @param paddle the block representing the paddle
     * @param keyboard the keyboard sensor used to control the paddle
     * @param color the color of the paddle
     * @param game the games.GameEnvironment in which the paddle exists
     */
    public Paddle(Block paddle, KeyboardSensor keyboard, Color color,
                  GameEnvironment game) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.color = color;
        this.game = game;
    }

    /**
     * Returns the rectangle representing the paddle.
     * @return the rectangle representing the paddle
     */
    public Block getRectangle() {
        return this.paddle;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double newPaddleX = this.paddle.getRectangle().getUpperLeft().getX() - SPEED;

        boolean isPaddleWithinBounds = this.paddle.getRectangle().getUpperLeft().getX() >= 0 + MAX_SIDE_GAP;

        if (isPaddleWithinBounds) {
            this.paddle.getRectangle().setNewUpperLeft(new Point(newPaddleX,
                    this.paddle.getRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newPaddleX = this.paddle.getRectangle().getUpperLeft().getX() + SPEED;
        int guiSizeX = WIDTH;

        boolean isPaddleWithinBounds = this.paddle.getRectangle().getLowerRight().getX() <= guiSizeX - MAX_SIDE_GAP;

        if (isPaddleWithinBounds) {
            this.paddle.getRectangle().setNewUpperLeft(new Point(newPaddleX,
                    this.paddle.getRectangle().getUpperLeft().getY()));
        }
    }

    /**
     * Returns the speed of the given velocity.
     * @param currentVelocity the velocity whose speed to return
     * @return the speed of the given velocity
     */
    public double pointToSpeed(Velocity currentVelocity) {
        return Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx()) + (currentVelocity.getDy()
                * currentVelocity.getDy()));
    }

    /**
     * Draws the paddle on the given surface.
     * @param surface the surface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getRectangle().getRectangle()
                        .getUpperLeft().getX(),
                (int) this.getRectangle().getRectangle().getUpperLeft()
                        .getY(),
                (int) this.getRectangle().getRectangle().getWidth(),
                (int) this.getRectangle().getRectangle().getHeight());
    }

    /**
     * This method is called when time passed in the game.
     * It checks if the right or left key is pressed and moves the paddle
     * accordingly.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
        if (this.keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    /**
     * Returns the collision rectangle of the paddle.
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddle.getRectangle().getUpperLeft(),
                this.paddle.getRectangle().getWidth(),
                this.paddle.getRectangle().getHeight());
    }

    /**
     * Returns the new velocity of the ball after hitting the paddle at the given
     * collision point with the current velocity.
     * @param collisionPoint  the point where the ball collides with the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after hitting the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if the ball hits the paddle from the side
        if (Math.abs(collisionPoint.getX() - this.paddle.getRectangle().getUpperLeft().getX()) <= EPSILON
                || Math.abs(collisionPoint.getX() - this.paddle.getRectangle().getLowerRight().getX()) <= EPSILON) {
            currentVelocity.setDx((-1) * currentVelocity.getDx());
            return currentVelocity;
        }

        double paddleWidth = this.paddle.getRectangle().getWidth();
        double startX = this.paddle.getRectangle().getUpperLeft().getX();
        // divide paddle to five regions
        double part = paddleWidth / REGIONS;
        double currentSpeed = pointToSpeed(currentVelocity);
        Velocity newVelocity = currentVelocity;

        // if the ball hits the paddle from above, check in which region of the paddle is the collision
        // and act accordingly.
        if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getUpperLeft().getY()) <= EPSILON) {
            if (collisionPoint.getX() > startX && collisionPoint.getX() <= startX + part) {
                // Hits first region of the paddle
                newVelocity = Velocity.fromAngleAndSpeed(THREE_HUNDRED_DEGREES, currentSpeed);
            } else if (collisionPoint.getX() > startX + part && collisionPoint.getX()
                    <= startX + (TWO_PARTS * part)) {
                // Hits second region of the paddle
                newVelocity = Velocity.fromAngleAndSpeed(THREE_THIRTY_DEGREES, currentSpeed);
            } else if (collisionPoint.getX() > startX + (TWO_PARTS * part) && collisionPoint.getX()
                    <= startX + (THREE_PARTS * part)) {
                // Hits the middle of the paddle
                newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
            } else if (collisionPoint.getX() > startX + (THREE_PARTS * part) && collisionPoint.getX()
                    <= startX + (FOUR_PARTS * part)) {
                // Hits fourth region of the paddle
                newVelocity = Velocity.fromAngleAndSpeed(THIRTY_DEGREES, currentSpeed);
            } else if (collisionPoint.getX() > startX + (FOUR_PARTS * part) && collisionPoint.getX()
                    < startX + paddleWidth) {
                // Hits fifth region of the paddle
                newVelocity = Velocity.fromAngleAndSpeed(SIXTY_DEGREES, currentSpeed);
            } else {
                // reflect vertically
                currentVelocity.setDy((-1) * currentVelocity.getDy());
                return currentVelocity;
            }
        }
        return newVelocity;
    }
    /**
     * Add to game.
     * @param game the game
     * @throws RuntimeException the runtime exception
     */
    public void addToGame(GameLevel game) throws RuntimeException {
        if (game == null) {
            throw new RuntimeException("The game is empty!");
        }
        game.addSprite(this);
        game.addCollidable(this);
    }
 }
