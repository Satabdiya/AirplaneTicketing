import java.io.Serializable;
import java.util.ArrayList;

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
}
