// Ben Davidian - 206844045

package intefaces;

import geometry.Point;
import geometry.Rectangle;
import sprites.Velocity;
import sprites.sobject.Ball;

/**
 * A Collidable interface represents an object that can be collided with other
 * objects in the game.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of the object.
     * @return the collision rectangle of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it was collided with at the given collision
     * point with the given velocity.
     * @param hitter the ball object that collided with the block
     * @param collisionPoint the point of collision
     * @param currentVelocity the velocity of the object before the collision
     * @return the new velocity of the object after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}