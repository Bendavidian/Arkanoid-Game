// Ben Davidian - 206844045

package sprites.sobject;
import biuoop.DrawSurface;

import intefaces.Collidable;
import collisions.CollisionInfo;
import collisions.cObject.Block;
import geometry.Point;

import geometry.Line;
import geometry.Rectangle;
import intefaces.HitListener;
import intefaces.HitNotifier;
import sprites.Velocity;
import intefaces.Sprite;

import game.GameEnvironment;
import game.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Ball class represents a circle with a center point, radius, and color.
 */
public class Ball implements HitNotifier, Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;
    public static final int MIN_RADIUS = 1;
    private static final Rectangle LEFT_BORDER = new Rectangle(new Point(0, 30), 20, 600);
    private static final Rectangle RIGHT_BORDER = new Rectangle(new Point(780, 30), 20, 600);
    private static final Rectangle UPPER_BORDER = new Rectangle(new Point(0, 24), 800, 22);
    private static final Rectangle BOTTOM_BORDER = new Rectangle(new Point(0, 620), 800, 20);
    private static final int MIN_X = 10;
    private static final int MAX_X = 790;
    private static final int MIN_Y = 10;
    private static final int MAX_Y = 590;



    /**
     * Creates a new Ball with the specified center point, radius, and color.
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     * @param gameEnvironment the game environment of the ball
     */
    public Ball(Point center, int radius, Color color, GameEnvironment gameEnvironment) {
        if (radius <= 0) {
            radius = MIN_RADIUS;
        }
        this.center = center;
        this.radius = radius;
        this.color = color;
        Velocity v = new Velocity(0, 0);
        this.velocity = v;
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Creates a new Ball with the specified x and y coordinates of the center
     * point, radius, and color.
     * @param x the x coordinate of the center point of the ball
     * @param y the y coordinate of the center point of the ball
     * @param radius the radius of the ball
     * @param color the color of the ball
     * @param gameEnvironment the game environment of the ball
     */
    public Ball(double x, double y, int radius, Color color, GameEnvironment
            gameEnvironment) {
        if (radius <= 0) {
            radius = MIN_RADIUS;
        }
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // Draws the ball on the surface.
        surface.setColor(this.color);
        surface.fillCircle(this.getBallCenterX(), this.getBallCenterY(), this.getBallSize());
        // Draws the border of the ball.
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getBallCenterX(), this.getBallCenterY(), this.getBallSize());
    }

    /**
     * Moves the ball one step according to its velocity and the game
     * environment.
    */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets the velocity of the ball.
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball.
     * @param dx the change in x-axis direction
     * @param dy the change in y-axis direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the game environment of the ball.
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * Set method given the new values of the center.
     * @param x x value of the new center point of the ball.
     * @param y y value of the new center point of the ball.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * Gets the velocity of the ball.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getBallCenterX method returns the x-coordinate of the center point of
     * the ball.
     * @return the x-coordinate of the center point of the ball (as an integer).
     */
    public int getBallCenterX() {
        return (int) this.center.getX();
    }

    /**
     * getBallCenterY method returns the y-coordinate of the center point of
     * the ball.
     * @return the y-coordinate of the center point of the ball (as an integer).
     */
    public int getBallCenterY() {
        return (int) this.center.getY();
    }

    /**
     * getSize is a method that returns the size value of the ball.
     * @return the size value of the ball.
     */
    public int getBallSize() {
        return this.radius;
    }

    /**
     * moveOneStep method is responsible for moving the ball one step according
     * to its velocity.
     * If there is no collision with any object, the ball will continue moving
     * normally.
     * If the ball collides with an object, it will be set to the closest
     * point before the collision, and its velocity will be updated according
     * to the hit collisions.object.
     */
    public void moveOneStep() {
        double x = this.center.getX();
        double y = this.center.getY();
        Line trajectory = new Line(x, y, x + this.velocity.getDx(),
                y + velocity.getDy());
        CollisionInfo collInfo = this.getGameEnvironment()
                .getClosestCollision(trajectory);
        // if there is no collision.
        if (collInfo == null) {
            // move the ball normally according to its velocity.
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Collidable collisionObject = this.gameEnvironment.
                    getClosestCollision(trajectory).collisionObject();
            Point temp = this.center;
            // If there is a collision, move the ball to the closest point
            // before the collision.
            moveToClosestPointBeforeCollision(x, y);
            // Update the velocity of the ball according to the hit object.
            this.setVelocity(collInfo.collisionObject().hit(this, collInfo
                    .collisionPoint(), this.velocity));
            if (gameEnvironment.isInBlock(this.center)) {
                setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
               this.center = this.getVelocity().applyToPoint(temp);
            }
            enteredTheBlock(collisionObject);
            enteredTheBlock(new Block(BOTTOM_BORDER, Color.GRAY));
            enteredTheBlock(new Block(UPPER_BORDER, Color.GRAY));
            enteredTheBlock(new Block(LEFT_BORDER, Color.GRAY));
            enteredTheBlock(new Block(RIGHT_BORDER, Color.GRAY));
        }
    }

    /**
     * The method checks if the ball inside a block and
     * the method pulls the ball outside the block.
     * @param collidable a block the ball entered into.
     */
    public void enteredTheBlock(Collidable collidable) {
        Rectangle rectangle = collidable.getCollisionRectangle();
        double leftX = rectangle.getUpperLeft().getX();
        double rightX = rectangle.getUpperRight().getX();
        double upperY = rectangle.getUpperLeft().getY();
        double lowerY = rectangle.getLowerLeft().getY();

        double x = this.center.getX();
        double y = this.center.getY();

        if ((leftX < x && x < rightX) && (upperY < y && y < lowerY)) {
            if (x < MIN_X) {
                x = MIN_X + 1;
            }
            if (x > MAX_X) {
                x = MAX_X - 1;
            }
            if (y < MIN_Y) {
                y = MIN_Y + 1;
            }
            if (y > MAX_Y) {
                y = MAX_Y - 1;
            }
            if (leftX < x && x < rightX && upperY < y && y < lowerY) {
                this.setCenter(x, upperY - this.radius * 2);
            }
            this.setCenter(x, y);
        }
    }

    /**
     * moveToClosestPointBeforeCollision method is responsible for moving
     * the ball to the closest point before the collision.
     * @param x the x-coordinate of the ball
     * @param y the y-coordinate of the ball
     */
    private void moveToClosestPointBeforeCollision(double x, double y) {
        if (this.velocity.getDx() < 0) {
            // The ball was moving to the left and hit object.
            // Set the ball to the right of the object.
            this.center = new Point(x + (double) this.radius / 2, y);
        }
        if (this.velocity.getDx() > 0) {
            // The ball was moving to the right and hit an object.
            // Set the ball to the left of the object.
            this.center = new Point(x - (double) this.radius / 2, y);
        }
        if (this.velocity.getDy() < 0) {
            // The ball was moving up and hit an object Set the ball below
            // the collisions.object.
            this.center = new Point(x, y + (double) this.radius / 2);
        }
        if (this.velocity.getDy() > 0) {
            // The ball was moving down and hit an object. Set the ball above
            // the collisions.object.
            this.center = new Point(x, y - (double) this.radius / 2);
        }
    }
    /**
     * addToGame method adds the ball to the given game.
     * @param game the game to add the ball to
     * @throws RuntimeException if the game is null
     */
    public void addToGame(GameLevel game) throws RuntimeException {
        if (game == null) {
            throw new RuntimeException("The game is empty!");
        }
        game.addSprite(this);
    }

    /**
     * Removes the ball from the game by removing it from the specified game.
     * @param game the game to remove the ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the ball.
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the ball.
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

