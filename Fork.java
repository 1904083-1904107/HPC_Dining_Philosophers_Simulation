import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Fork class to represent each fork in the dining philosophers problem
public class Fork {
    private final Lock lock = new ReentrantLock(); // Lock to manage access to the fork

    // Method to pick up the fork (locks the fork)
    public void pickUp() {
        lock.lock(); // Acquire the lock to pick up the fork
    }

    // Method to put down the fork (unlocks the fork)
    public void putDown() {
        lock.unlock(); // Release the lock when the fork is no longer needed
    }

    // Method to try to pick up the fork without blocking
    public boolean tryPickUp() {
        return lock.tryLock(); // Attempt to acquire the lock, returns true if successful
    }
}
