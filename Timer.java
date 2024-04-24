public class Timer implements Event {
    public static int nextId = 0; // static var to keep track of the next timer ID
    public int id; //  identifier for the timer
    public int duration; // timer duration
    public int insertionTime; // Time when the timer was inserted into the event list
    public int arrivalTime; // Time when the timer is expected to arrive

    // Constructor to initialize the timer with a given duration
    public Timer(int duration) {
        this.id = nextId++; // assigns unique ID to the timer and increment nextId
        this.duration = duration; // sets the duration of the timer
    }

    // Method to set the insertion time and calculate the arrival time of the timer
    @Override
    public void setInsertionTime(int currentTime) {
        this.insertionTime = currentTime; // sets insertion time to current simulation time
        this.arrivalTime = currentTime + duration; // calculates the arrival time based on the current time and duration
    }

    // Method to get the insertion time of the timer
    @Override
    public int getInsertionTime() {
        return insertionTime; // return the insertion time
    }

    // Method to get the arrival time of the timer
    @Override
    public int getArrivalTime() {
        return arrivalTime; // return the arrival time
    }

    // Method to cancel the timer and print a msg
    @Override
    public void cancel(int currentTime) {
        System.out.println("Timer " + id + " canceled at time: " + currentTime); // prints msg indicating timer cancellation
    }

    // Method to handle the timer and print a msg
    @Override
    public void handle() {
        System.out.println("Timer " + id + " handled (arrival time: " + arrivalTime + ")"); // prints msg indicating timer handling
    }

    // Method to provide a string representation of the timer
    @Override
    public String toString() {
        return "- Timer " + id + " (insertion time: " + insertionTime + ", arrival time: " + arrivalTime + ")"; // returns string containing timer information
    }
}
