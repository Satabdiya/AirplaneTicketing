import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public final class ReservationClient {

    public static void main(String[] args) {
        String hostname;
        String portString = "";
        int port;
        Socket socket;
        boolean correctPort = false;
        try {
            hostname = JOptionPane.showInputDialog(null, "What is the hostname you'd like to connect to?", "Hostname?", JOptionPane.QUESTION_MESSAGE);
            while (!correctPort) {
                portString = JOptionPane.showInputDialog(null, "What is the port you'd like to connect to?", "Port?", JOptionPane.QUESTION_MESSAGE);
                if (portString == null) {
                    JOptionPane.showMessageDialog(null, "Your port cannot be empty.", "Error!", JOptionPane.PLAIN_MESSAGE);
                } else if (!isParsable(portString)) {
                    JOptionPane.showMessageDialog(null, "Your port cannot be negative and must contain five digits.", "Error!", JOptionPane.PLAIN_MESSAGE);
                } else {
                    port = Integer.parseInt(portString);
                    correctPort = true;
                }
            }
            port = Integer.parseInt(portString);
            socket = new Socket(hostname, port);

            JFrame frame = new JFrame("Purdue University Flight Reservation System");
            JPanel panel = new JPanel();
            JButton exitButton = new JButton("Exit");
            JButton bookFlightButton = new JButton("Book a Flight");
            frame.setSize(300, 250);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JTextField textField = new JTextField("Welcome to the Purdue University Airline Reservation\n" +
                    "Management System!");
            panel.add(textField);
            panel.add(exitButton);
            panel.add(bookFlightButton);
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    frame.setVisible(false);
                }
            });
            bookFlightButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }
}

class ResponseListener{

}