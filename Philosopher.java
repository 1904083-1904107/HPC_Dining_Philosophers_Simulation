import java.util.Random;

// Philosopher class that represents each philosopher
public class Philosopher extends Thread {
    private final int id; // Unique identifier for the philosopher
    private final String label; // Label (A-Z) for the philosopher
    private final Fork leftFork; // Left fork reference
    private final Fork rightFork; // Right fork reference
    private final Table table; // Reference to the table the philosopher is seated at
    private boolean hungry = false; // Flag to check if philosopher is hungry

    // Constructor to initialize a philosopher
    public Philosopher(int id, Fork leftFork, Fork rightFork, Table table, String label) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.table = table;
        this.label = label;
    }

    // Main execution method for the philosopher's behavior
    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                // Step 1: Think for a random time (0-10 seconds)
                think(random);

                // Step 2: Try to pick up the left fork
                System.out.println(label + " is hungry and trying to pick up the left fork.");
                leftFork.pickUp(); // Pick up the left fork
                System.out.println(label + " picked up the left fork.");

                // Step 3: Wait for 4 seconds before trying to pick up the right fork
                Thread.sleep(4000);

                // Step 4: Try to pick up the right fork
                System.out.println(label + " is trying to pick up the right fork.");
                if (rightFork.tryPickUp()) { // Try to pick up the right fork
                    System.out.println(label + " picked up the right fork.");
                    // Step 5: Eat for a random time (0-5 seconds)
                    eat(random);

                    // Step 6 & 7: Put down both forks after eating
                    rightFork.putDown(); // Put down the right fork
                    System.out.println(label + " put down the right fork.");
                    leftFork.putDown(); // Put down the left fork
                    System.out.println(label + " put down the left fork.");
                } else {
                    System.out.println(label + " couldn't pick up the right fork.");
                    // Deadlock occurred, put down left fork
                    //leftFork.putDown(); // Put down the left fork if right fork is not available
                    hungry = true; // Mark as hungry to indicate deadlock
                }
            }
        } catch (InterruptedException e) {
            System.out.println(label + " was interrupted.");
        }
    }

    // Method to simulate thinking for a random time
    private void think(Random random) throws InterruptedException {
        int thinkTime = random.nextInt(10) * 1000; // Generate random think time (0-10 seconds)
        System.out.println(label + " is thinking for " + (thinkTime / 1000) + " seconds.");
        Thread.sleep(thinkTime); // Simulate thinking
    }

    // Method to simulate eating for a random time
    private void eat(Random random) throws InterruptedException {
        int eatTime = random.nextInt(5) * 1000; // Generate random eat time (0-5 seconds)
        System.out.println(label + " is eating for " + (eatTime / 1000) + " seconds.");
        Thread.sleep(eatTime); // Simulate eating
    }

    // Method to check if the philosopher is hungry
    public boolean isHungry() {
        return hungry; // Return the hungry flag
    }

    // Method to reset the hungry state
    public String getLabel() {
        return label; // Get the philosopher's label
    }

    // Method to reset the hungry state
    public void resetHungry() {
        hungry = false; // Reset the hungry flag
    }
}
