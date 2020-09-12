import java.io.Serializable;
import java.util.ArrayList;

// Playing around
/**
 * Airline
 * <p>
 * This class defines the Airline Abstract Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public abstract class Airline implements Serializable {
    private final String airlineName; // the airline's name
    private final String flightNumber; // the flight number associated with the only available flight
    private int capacity;
    private Gate gate;

    public Airline(String airlineName, String flightNumber, int capacity) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        gate = new Gate(this);
    }

    public Airline(String airlineName, String flightNumber, int capacity, String gate) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.gate = new Gate(this, gate);
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public Gate getGate() {
        return gate;
    }

    /**
     * This returns a string with the description of the airline.
     *
     * @return a string with the airline description
     */
    public abstract String getInfo();

    public String returnThis() {
        return String.format("%s %s %d %s", airlineName, flightNumber, capacity, gate.getGate());
    }
}
