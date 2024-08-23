// Ben Davidian - 206844045

package listeners;

/**
 * The Counter class represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int value;

    /**
     * Constructs a Counter object with the initial value.
     * @param value the initial value of the counter
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Decreases the value of the counter by the specified number.
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Increases the value of the counter by the specified number.
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Returns the current value of the counter.
     * @return the current value of the counter
     */
    public int getValue() {
        return this.value;
    }
}
