import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayEventList eventList = new ArrayEventList(); // creates an array list to store timers
        Timer[] timers = new Timer[10000]; //  maximum 10000 timers as a safe number
        
        try {
            File file = new File("events.txt"); //  file object that read events from a valid file named "events.txt"
            Scanner scanner = new Scanner(file); // creates a scanner to read from the file
            while (scanner.hasNextLine()) { // loops through each line in the file
                String line = scanner.nextLine(); // reads next line
                char command = line.charAt(0); // extracts command from current line
                switch (command) {
                    case 'I':
                        int duration = Integer.parseInt(line.substring(2)); // gets duration from current line
                        Timer timer = new Timer(duration); // Create a new timer with the given duration
                        eventList.insert(timer); // inserts timer into the event list
                        timers[timer.id] = timer; // stores timer in the array for future reference
                        System.out.println(timer); // Print information about the newly created timer
                        break;
                    case 'R':
                        Event event = eventList.removeFirst(); // removes the first event from the event list if the case is R
                        if (event != null) { // if event was removed successfully
                            event.handle(); // handles removed event
                        }
                        break;
                    case 'C':
                        int timerId = Integer.parseInt(line.substring(2)); // extract timer ID from the line
                        Timer timerToCancel = timers[timerId]; // get the timer to cancel from the array
                        if (timerToCancel != null) { // if timer exists
                            eventList.remove(timerToCancel); // remove the timer from the event list
                            timerToCancel.cancel(eventList.getSimulationTime()); // then cancel the timer
                        }
                        break;
                    default:
                        System.out.println("Invalid command: " + command); // defaut case for invalid commands
                }
            }
            scanner.close(); // close the scanner after reading file 
        } catch (FileNotFoundException e) { 
            System.out.println("An error occurred."); // prints an error message
            e.printStackTrace(); // prints the stack trace for the exception
        }
        System.out.println("Future event list size: " + eventList.size()); // prints the size of the event list
        System.out.println("Future event list capacity: " + eventList.capacity()); // prints the capacity of the event list
    }
}
