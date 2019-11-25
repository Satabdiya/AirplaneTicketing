import java.io.Serializable;

public class BoardingPass implements Serializable {
    private Passenger passenger; // the passenger that this boarding pass belongs to
    private Gate gate; // the gate at which this boarding pass will be valid

    public BoardingPass(Passenger passenger, Gate gate) {
        this.passenger = passenger;
        this.gate = gate;
    }

    @Override
    /**
     * This method returns a formatted string that displays the boarding pass.
     */
    public String toString() {
        return String.format("-------------------------------------------------------------------------------\n" +
                        "BOARDING PASS FOR FLIGHT %s WITH %s\n" +
                        "PASSENGER FIRST NAME : %s\n" +
                        "PASSENGER LAST NAME : %s\n" +
                        "PASSENGER AGE : %d\n" +
                        "You can now begin boarding at gate %s" +
                        "-------------------------------------------------------------------------------"
                , gate.getAirline().getFlightNumber(), gate.getAirline().getAirlineName(), passenger.getFirstName(),
                passenger.getLastName(), passenger.getAge(), gate.getGate());
    }
}
