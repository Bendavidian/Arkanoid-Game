// Ben Davidian - 206844045

package intefaces;

/**
 * The HitNotifier interface indicates that objects that implement it send notifications when they are being hit.
 **/

public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to hit events.
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners to hit events.
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
