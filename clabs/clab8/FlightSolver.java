import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    public ArrayList<Flight> flights;
    private PriorityQueue<Integer> maxPassengers = new PriorityQueue<>();
    public FlightSolver(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public int solve() {
        for (int i = 0; i < flights.size(); i += 1) {
            int passengers = flights.get(i).passengers;
            for (int j = i + 1; j < flights.size(); j += 1) {
                if (flights.get(i).endTime() > flights.get(j).startTime()) {
                    passengers += flights.get(j).passengers;
                } else {
                    break;
                }
            }
            maxPassengers.add(-passengers);
        }
        return -maxPassengers.peek();
    }

}
