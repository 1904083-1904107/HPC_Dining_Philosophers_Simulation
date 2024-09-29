import java.util.ArrayList;
import java.util.List;

// Table class that contains forks and philosophers
public class Table {
    private final int id; // Unique identifier for the table
    private final Fork[] forks; // Array of forks at the table
    private final List<Philosopher> philosophers; // List of philosophers seated at the table

    // Constructor initializes forks and the philosopher list
    public Table(int id) {
        this.id = id; // Set the table ID
        this.forks = new Fork[5]; // Create array for 5 forks
        this.philosophers = new ArrayList<>(); // Initialize the philosopher list
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(); // Create a new fork for each position
        }
    }

    // Method to get the left fork of a philosopher
    public Fork getLeftFork(int philosopherIndex) {
        return forks[philosopherIndex];
    }

    // Method to get the right fork of a philosopher
    public Fork getRightFork(int philosopherIndex) {
        return forks[(philosopherIndex + 1) % 5];
    }

    // Method to add a philosopher to the table
    public void addPhilosopher(Philosopher philosopher) {
        philosophers.add(philosopher);
    }

    // Method to retrieve the list of philosophers
    public List<Philosopher> getPhilosophers() {
        return philosophers;
    }

    // Method to check if the table is in a deadlocked state
    public boolean isDeadlocked() {
        for (Philosopher p : philosophers) {
            if (!p.isHungry()) { // If any philosopher is not hungry, it's not deadlocked
                return false;
            }
        }
        if(philosophers.size()==5)return true;// All philosophers are hungry; deadlock condition met
        else return false; 
    }

    // Getter for table ID
    public int getId() {
        return id;
    }
    public void removePhilosopher(int index){
        philosophers.remove(index);
    }
}
