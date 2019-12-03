import java.io.Serializable;

/**
 * BoardingPass
 * <p>
 * This class defines the BoardingPass Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
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
        return String.format("<html>-------------------------------------------------------------------------" +
                        "-------------------------------------------------------------------<br>" +
                        "BOARDING PASS FOR FLIGHT %s WITH %s<br>" +
                        "PASSENGER FIRST NAME : %s<br>" +
                        "PASSENGER LAST NAME : %s<br>" +
                        "PASSENGER AGE : %d<br>" +
                        "You can now begin boarding at gate %s<br>" +
                        "------------------------------------------------------------------------------------------" +
                        "--------------------------------------------------</html>"
                , gate.getAirline().getFlightNumber(), gate.getAirline().getAirlineName(), passenger.getFirstName(),
                passenger.getLastName(), passenger.getAge(), gate.getGate());
    }
}
