/**
 * Delta
 * <p>
 * This class defines the Delta Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public class Delta extends Airline {

    public Delta(String flightNumber) {
        super("Delta Airlines", flightNumber, 200);
    }

    public Delta(String flightNumber, String gate) {
        super("Delta Airlines", flightNumber, 100, gate);
    }

    public String getInfo() {
        return "Delta Airlines is proud to be one of the five premier Airlines at Purdue University.\n" +
                "We are extremely proud to offer exceptional services, with free limited WiFi for all customers.\n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.\n" +
                "We are also happy to offer power outlets in each seat for passenger use.\n" +
                "We hope you choose to fly Delta as your next Airline.";
    }
}
