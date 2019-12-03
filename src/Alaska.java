/**
 * Alaska
 * <p>
 * This class defines the Alaska Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public class Alaska extends Airline {

    public Alaska(String flightNumber) {
        super("Alaska Airlines", flightNumber, 100);
    }

    public Alaska(String flightNumber, String gate) {
        super("Alaska Airlines", flightNumber, 100, gate);
    }

    public String getInfo() {
        return "Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue University.\n" +
                "We primarily fly westward, and often have stops in Alaska and California.\n" +
                "We have first class amenities, even in coach class.\n" +
                "We provide fun snacks, such as pretzels and goldfish.\n" +
                "We also have comfortable seats, and free WiFi.\n" +
                "We hope you choose Alaska Airlines for your next itinerary!";
    }
}
