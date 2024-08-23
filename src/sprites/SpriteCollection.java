// Ben Davidian - 206844045

package sprites;

import biuoop.DrawSurface;
import intefaces.Sprite;

import java.util.ArrayList;

/**
 * A collection of sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spritesCollection;

    /**
     * Constructor for the sprite collection.
     */
    public SpriteCollection() {
        this.spritesCollection = new ArrayList<>();
    }

    /**
     * Returns a new ArrayList with the sprites in the collection.
     * @return an ArrayList with the sprites in the collection.
     */
    public ArrayList<Sprite> getSprites() {
        return new ArrayList<Sprite>(this.spritesCollection);
    }

    /**
     * Adds a sprite to the collection.
     * @param sprite the sprite to add.
     */
    public void addSprite(Sprite sprite) {
            this.spritesCollection.add(sprite);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite: this.getSprites()) {
            sprite.timePassed();
        }
    }

    /**
     * Removes a sprite from the collection.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            this.spritesCollection.remove(s);
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: this.getSprites()) {
            sprite.drawOn(d);
        }
    }
}
