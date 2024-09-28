import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiningPhilosophersSimulation {
    private static final int NUM_TABLES = 5; // Number of tables
    private static final int NUM_PHILOSOPHERS_PER_TABLE = 5; // Number of philosophers per table
    private static final Table sixthTable = new Table(6); // Sixth table for moving philosophers
    private static final List<Table> tables = new ArrayList<>(); // List of tables
    private static Philosopher lastPhilosopherMoved = null; // To track the last philosopher moved
    private static long startTime; // To track when the simulation starts
    private static long deadlockTime; // To track when the sixth table enters deadlock

    // Main method to start the simulation
    public static void main(String[] args) throws InterruptedException {
        initializeTables(); // Initialize tables
        initializePhilosophers(); // Initialize philosophers

        // Start all philosophers
        for (Table table : tables) {
            for (Philosopher philosopher : table.getPhilosophers()) {
                philosopher.start(); // Start each philosopher thread
            }
        }

        // Set the start time
        startTime = System.currentTimeMillis();

        // Monitor for deadlock
        while (true) {
            for (Table table : tables) {
                if (table.isDeadlocked()) { // Check if the current table is deadlocked
                    moveRandomPhilosopherToSixthTable(table); // Move a random hungry philosopher to the sixth table
                }
            }

            // Check if the sixth table is deadlocked
            if (sixthTable.isDeadlocked()) {
                deadlockTime = System.currentTimeMillis(); // Record the deadlock time
                long elapsedTime = deadlockTime - startTime; // Calculate the elapsed time
                System.out.println("The 6th table has deadlocked.");
                System.out.println(
                        "Elapsed time for the 6th table to enter deadlock: " + (elapsedTime / 1000.0) + " seconds.");
                System.out.println(
                        "The last philosopher to move to the 6th table was " + lastPhilosopherMoved.getLabel());
                return; // Exit the monitoring loop
            }

            Thread.sleep(1000); // Slow down monitoring to avoid CPU overuse
        }
    }

    // Method to initialize the tables
    private static void initializeTables() {
        for (int i = 0; i < NUM_TABLES; i++) {
            tables.add(new Table(i)); // Create and add tables to the list
        }
    }

    // Method to initialize the philosophers at each table
    private static void initializePhilosophers() {
        int philosopherIndex = 0; // Index to assign labels
        for (Table table : tables) {
            for (int i = 0; i < NUM_PHILOSOPHERS_PER_TABLE; i++) {
                String label = String.valueOf((char) ('A' + philosopherIndex)); // Create label A-Z
                philosopherIndex++;
                Fork leftFork = table.getLeftFork(i); // Get left fork for the philosopher
                Fork rightFork = table.getRightFork(i); // Get right fork for the philosopher
                Philosopher philosopher = new Philosopher(i, leftFork, rightFork, table, label); // Create philosopher
                table.addPhilosopher(philosopher); // Add philosopher to the table
            }
        }
    }

    // Method to move a random philosopher to the sixth table if deadlock occurs
    private static void moveRandomPhilosopherToSixthTable(Table table) {
        List<Philosopher> hungryPhilosophers = new ArrayList<>(); // List to hold hungry philosophers

        // Collect all hungry philosophers
        for (Philosopher philosopher : table.getPhilosophers()) {
            if (philosopher.isHungry()) {
                hungryPhilosophers.add(philosopher); // Add hungry philosopher to the list
            }
        }

        // If there are hungry philosophers, choose one randomly
        if (!hungryPhilosophers.isEmpty()) {
            Random random = new Random();
            Philosopher selectedPhilosopher = hungryPhilosophers.get(random.nextInt(hungryPhilosophers.size())); // Randomly select a philosopher
                                                                                                                 
            System.out.println(selectedPhilosopher.getLabel() + " moved to the 6th table.");
            lastPhilosopherMoved = selectedPhilosopher; // Update the last philosopher who moved
            sixthTable.addPhilosopher(selectedPhilosopher); // Move to the sixth table
            selectedPhilosopher.interrupt(); // Interrupt the philosopher's thread
        }
    }
}
