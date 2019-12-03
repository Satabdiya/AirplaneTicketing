import java.io.Serializable;

/**
 * Passenger
 * <p>
 * This class defines the Passenger Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public class Passenger implements Serializable {

    private String firstName; // first name of the passenger
    private String lastName; // last name of the passenger
    private int age; // age of the passenger
    private BoardingPass pass;

    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.age = age;
        pass = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void boughtTicket(BoardingPass boardingPass) {
        this.pass = boardingPass;
    }

    /**
     * This method generates a string that represents the passenger's information in the reservation file.
     *
     * @return a string that will be added to the reservations.txt file after the reservation is made
     */
    public String stringToAddToFile() {
        return String.format("%s. %s, %d", firstName.charAt(0), lastName, age);
    }
}
