// Ben Davidian - 206844045
package collisions;

import geometry.Point;
import intefaces.Collidable;

/**
 * A class that holds information about a collision point and the object
 * it collided with.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the collision point.
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     Returns the collidable object collided with.
     @return the collidable object collided with
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
