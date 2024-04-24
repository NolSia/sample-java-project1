import java.util.Arrays;

public class ArrayEventList implements FutureEventList {
    private static final int startingCap = 5; // starting capacity of the events array
    private Event[] events; // events stored in array
    private int size; // current number of events in the array
    private int simulationTime; // current simulation time

    // Constructor to initialize the ArrayEventList
    public ArrayEventList() {
        events = new Event[startingCap]; 
        size = 0; 
        simulationTime = 0; 
    }

    // Method to resize the events array when needed
    private void resize() {
        int newCapacity = events.length * 2; // doubles the current capacity
        events = Arrays.copyOf(events, newCapacity); 
    }

    // Method to remove and return the first event from the list
    @Override
    public Event removeFirst() {
        if (size == 0) return null; 
        Event removed = events[0]; // stores the event to be removed
        System.arraycopy(events, 1, events, 0, size - 1); 
        size--; 
        simulationTime = removed.getArrivalTime(); 
        return removed; // returns removed event
    }

    // Method to remove a specific event from the list
    @Override
    public boolean remove(Event e) {
        int index = Arrays.binarySearch(events, 0, size, e, (event1, event2) -> {
            int time1 = event1.getArrivalTime();
            int time2 = event2.getArrivalTime();
            return Integer.compare(time1, time2);
        }); 
        if (index >= 0) {
            System.arraycopy(events, index + 1, events, index, size - index - 1); // shifts elements to the left to remove the event
            size--; // lowers the size
            return true; // return true indicating event removal
        }
        return false; 
    }

    // Method to insert an event into the list
    @Override
    public void insert(Event e) {
        if (size == events.length) { // if array is full
            resize(); // resize the array
        }
        events[size++] = e; // inserts the event at the end of the array and increment size
        e.setInsertionTime(simulationTime); // sets the insertion time of the event to current simulation time
        Arrays.sort(events, 0, size, (event1, event2) -> {
            int time1 = event1.getArrivalTime();
            int time2 = event2.getArrivalTime();
            return Integer.compare(time1, time2);
        }); 
    }

    // Method to get the current size of the event list
    @Override
    public int size() {
        return size; // returns the current size
    }

    // Method to get the capacity of the event list
    @Override
    public int capacity() {
        return events.length; // return the capacity of the events array
    }

    // Method to get the current simulation time
    @Override
    public int getSimulationTime() {
        return simulationTime; // Return the current simulation time
    }
}
