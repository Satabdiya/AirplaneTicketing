import java.io.Serializable;
import java.util.Random;

/**
 * Gate
 * <p>
 * This class defines the Gate Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public class Gate implements Serializable {
    private String terminal; // the terminal representing the gate
    private int gateNumber;
    private Airline airline; // the airline currently at the gate

    public Gate(Airline airline) {
        this.terminal = Character.toString(new Random().nextInt(3) + 65);
        this.gateNumber = new Random().nextInt(19);
        this.airline = airline;
    }

    public Gate(Airline airline, String gate) {
        this.terminal = Character.toString(gate.charAt(0));
        this.gateNumber = Integer.parseInt(gate.substring(1));
        this.airline = airline;
    }

    public String getGate() {
        return terminal + gateNumber;
    }

    public Airline getAirline() {
        return airline;
    }
}
