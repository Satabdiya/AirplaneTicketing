import java.io.Serializable;
import java.util.Random;

public class Gate implements Serializable {
    private String terminal; // the terminal representing the gate
    private int gateNumber;
    private Airline airline; // the airline currently at the gate

    public Gate(Airline airline) {
        this.terminal = Character.toString(new Random().nextInt(3) + 65);
        this.gateNumber = new Random().nextInt(19);
        this.airline = airline;
    }

    public String getGate() {
        return terminal + gateNumber;
    }

    public Airline getAirline() {
        return airline;
    }
}
