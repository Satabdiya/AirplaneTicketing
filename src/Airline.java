import java.io.Serializable;
import java.util.ArrayList;

public abstract class Airline implements Serializable {
    private final String airlineName; // the airline's name
    private final String flightNumber; // the flight number associated with the only available flight
    private ArrayList<Passenger> passengers; // the list of passengers who bought a ticket from this airline

    public Airline(String airlineName, String flightNumber) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        passengers = new ArrayList<>(0);
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * This method adds the given passenger to the airline's Passenger list.
     *
     * @param passenger the passenger to be added to the airline's system tracking the tickets sold
     */
    public void addPassengers(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * This returns a string with the description of the airline.
     *
     * @return a string with the airline description
     */
    public abstract String getInfo();
}
