/**
 * Southwest
 * <p>
 * This class defines the Southwest Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public class Southwest extends Airline {
    public Southwest(String flightNumber) {
        super("Southwest Airlines", flightNumber, 100);
    }

    public Southwest(String flightNumber, String gate) {
        super("Southwest Airlines", flightNumber, 100, gate);
    }

    public String getInfo() {
        return "Southwest Airlines is proud to offer flights to Purdue university.\n" +
                "We are happy to offer free in flight wifi, as well as our amazing snacks.\n" +
                "In addition, we offer flights for much cheaper than other airlines, and offer two free " +
                "checked bags.\n" +
                "We hope you choose Southwest for your next flight.";
    }
}
