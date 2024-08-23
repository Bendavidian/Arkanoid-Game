// Ben Davidian - 206844045

package game;

import intefaces.Collidable;
import collisions.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment in which the game
 * takes place.
 * It contains a list of Collidable objects that may interact with other
 * objects in the game.
 */
public class GameEnvironment {
    // A list of all Collidable objects in the game environment.
    private final List<Collidable> collidableObjects;

    /**
     * Constructs a GameEnvironment object with an empty list of Collidable objects.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<>();
    }

    /**
     * Returns the number of Collidable objects in the game environment.
     * @return the number of Collidable objects
     */
    public int getSize() {
        return this.collidableObjects.size();
    }

    /**
     * Adds a Collidable object to the list of Collidable objects in the environment.
     * @param c a Collidable object to be added to the environment
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidableObjects.add(c);
        }
    }

    /**
     * Adds a Collidable object to the list of Collidable objects in the environment.
     * @param c a Collidable object to be added to the environment
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidableObjects.remove(c);
        }
    }

    /**
     * Finds the closest collision point between a given trajectory and any Collidable object in the environment.
     * @param trajectory the Line representing the trajectory of an object in the game
     * @return a CollisionInfo object representing the closest collision point, or null if there is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> candidateCollisions = new ArrayList<>();
        candidateCollisions.addAll(this.collidableObjects);
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;
        // Check if there are any collidable objects
        if (candidateCollisions.isEmpty()) {
            return null;
        }
        // Find the closest collision point
        for (Collidable candidateCollision : candidateCollisions) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(candidateCollision
                    .getCollisionRectangle());
            if (intersection != null) {
                if (closestCollisionPoint == null || trajectory.getStart().distance(intersection)
                        < trajectory.getStart().distance(closestCollisionPoint)) {
                    closestCollisionPoint = intersection;
                    closestCollidable = candidateCollision;
                }
            }
        }
        if (closestCollisionPoint == null) {
            return null;
        }
        return new CollisionInfo(closestCollisionPoint, closestCollidable);
    }

    /**
     * Checks if a point is inside a block of collidable objects.
     * @param check the point to check if it's inside a block
     * @return true if the point is inside a block of collidable objects,
     * false otherwise
     */
    public boolean isInBlock(Point check) {
        for (int i = 0; i < this.collidableObjects.size() - 1; i++) {
            if (check.getX() >= collidableObjects.get(i).getCollisionRectangle()
                    .getUpperLeft().getX() && check.getX() <= collidableObjects
                    .get(i).getCollisionRectangle().getUpperRight().getX()
                    && check.getY() <= collidableObjects.get(i)
                    .getCollisionRectangle().getUpperLeft().getY()
                    && check.getY() >= collidableObjects.get(i)
                    .getCollisionRectangle().getLowerLeft().getY()) {
                return true;
            }
        }
        return false;
    }
}


