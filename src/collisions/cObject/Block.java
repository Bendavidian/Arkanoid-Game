// Ben Davidian

package collisions.cObject;

import biuoop.DrawSurface;
import intefaces.Collidable;
import geometry.Point;
import geometry.Rectangle;
import geometry.Line;
import sprites.Velocity;
import intefaces.Sprite;
import game.GameLevel;
import intefaces.HitListener;
import intefaces.HitNotifier;
import sprites.sobject.Ball;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The Block class represents a block that can be collided with by other
 * objects.
 * It implements the Collidable, HitNotifier and Sprite interfaces.
 * A block is defined by a rectangle shape, a color, and methods for
 * determining collision and for drawing itself on a DrawSurface object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private java.util.ArrayList<HitListener> hitListeners;
    private static final int BOTTOM = 2;
    private static final int TOP = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 3;

    /**
     * Constructs a Block object with a given rectangle shape and color.
     * @param rect  the rectangle shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new java.util.ArrayList<>();
    }

    /**
     * Returns the collision rectangle of this block.
     * @return the collision rectangle of this block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Returns a new velocity object based on the current velocity of
     * an object and the collision point between the objects and this block.
     * @param collisionPoint  the collision point between objects
     * @param currentVelocity the current velocity of the object that collides with the block
     * @return a new velocity object based on the collision point and current velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity = new Velocity(dx, dy);
        // get lines of the CollisionRectangle
        List rectLines = this.rect.getRectLines();
        // check if the collision is on a line
        Rectangle blockRect = this.getCollisionRectangle();
        for (int i = 0; i < rectLines.size(); i++) {
            // Check if collision is on corner
            if (isCornerCollision(collisionPoint, blockRect)) {
                newVelocity = new Velocity(-dx, -dy);
            }
            if (((Line) rectLines.get(i)).isBetween(collisionPoint)) {
                if (i == LEFT || i == RIGHT) {
                    newVelocity = new Velocity(-dx, dy);
                } else if (i == BOTTOM || i == TOP) {
                    newVelocity = new Velocity(dx, -dy);
                }
            }
        }
        // Notifies to the whole listeners about the hit.
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * Returns the rectangle shape of this block.
     * @return the rectangle shape of this block
     */
    public Rectangle getRectangle() {
        return this.rect;
    }

    /**
     * This method does nothing for this class, since blocks don't change
     * over time.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Sets the color of the block.
     * @param color the color to set for the block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the block on a given DrawSurface collisions.object.
     * @param d the DrawSurface object to draw the block on
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Draws the block on the surface.
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        if (this.color == Color.GRAY) {
            // Color of gray to the gray borders
            d.setColor(Color.GRAY);
            d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        } else {
            // Draws the borders of the current rectangle
            d.setColor(Color.BLACK);
            d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }
    /**
     * Determines if a collision occurred on a corner of the block.
     * @param collisionPoint the point of collision
     * @param blockRect the collision rectangle of the block
     * @return true if the collision occurred on a corner, false otherwise
     */
    private boolean isCornerCollision(Point collisionPoint,
                                      Rectangle blockRect) {
        return collisionPoint.equals(blockRect.getUpperLeft())
                || collisionPoint.equals(blockRect.getUpperRight())
                || collisionPoint.equals(blockRect.getLowerLeft())
                || collisionPoint.equals(blockRect.getLowerRight());
    }

    /**
     * Removes the block from the game by removing it as a collidable and a sprite.
     * @param game the game from which to remove the block
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block.
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new ArrayList<>();
            hitListeners.add(hl);
        }
        this.hitListeners.add(hl);
    }
    /**
     * Remove a hit listener from the block.
     * @param hl the HitListener to be removed
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * Notifies the hit listeners that a hit event occurred.
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener listener : listeners) {
            listener.hitEvent(this, hitter);
        }
    }

    /**
     * Adds the current collidable and sprite to the given game.
     * Throws a RuntimeException if the game is null.
     * @param game the game to add the collidable and sprite to
     * @throws RuntimeException if the game is null
     */
    public void addToGame(GameLevel game) throws RuntimeException {
        if (game == null) {
            throw new RuntimeException("The game is empty!");
        }
        game.addCollidable(this);
        game.addSprite(this);
    }
}
