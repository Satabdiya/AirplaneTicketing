import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * ReservationClient
 * <p>
 * This class defines the ReservationClient Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public final class ReservationClient {

    static JFrame frame;
    static JPanel panel;
    static JFrame popUp;
    static JPanel encapsulate;
    static BufferedWriter socketWriter = null;
    static Socket socket;
    static ArrayList<String> flightsAvailable;
    static ArrayList<String> passengerList;
    static int capacity;
    static Airline selectedAirline = null;

    public static void main(String[] args) {
        String hostname = "";
        String portString;
        int port;
        boolean correctHost = false;
        boolean correctPort = false;

        try {
            while (!correctHost) {
                hostname = JOptionPane.showInputDialog(null, "What is the hostname " +
                        "you'd like to connect to?", "Hostname?", JOptionPane.QUESTION_MESSAGE);
                if (hostname == null) {
                    return;
                } else if (!hostname.equals("localhost")) {
                    JOptionPane.showMessageDialog(null, "Your hostname is not correct.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    correctHost = true;
                }
            }
            while (!correctPort) {
                portString = JOptionPane.showInputDialog(null, "What is the port " +
                        "you'd like to connect to?", "Port?", JOptionPane.QUESTION_MESSAGE);
                if (portString == null) {
                    return;
                }
                if (portString.equals("")) {
                    JOptionPane.showMessageDialog(null, "Your port cannot be empty.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (!isParsable(portString)) {
                    JOptionPane.showMessageDialog(null, "Your port cannot be negative.",
                            "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    port = Integer.parseInt(portString);
                    try {
                        socket = new Socket(hostname, port);
                        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        passengerList = new ArrayList<>(0);
                        correctPort = true;
                    } catch (ConnectException e) {
                        JOptionPane.showMessageDialog(null, "You entered an unrecognized" +
                                " port number!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Purdue University Flight Reservation System");
            frame.setSize(700, 500);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
            panel = new JPanel();
            popUp = new JFrame();
            popUp.setResizable(false);
            popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popUp.setSize(200, 200);
            encapsulate = new JPanel();
            welcome();
            frame.setVisible(true);
        });
    }

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }

    public static void welcome() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel();
        label.setText("Welcome to the Purdue University Airline Reservation Management System!");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon logo = new ImageIcon("Icon.png");
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Book a Flight");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> bookAFlight());
        buttons.add(button1);
        buttons.add(button2);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(label);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(logoLabel);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(buttons);
        panel.setVisible(true);
        frame.add(panel);
    }

    public static void bookAFlight() {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel();
        label.setText("Do you want to book a flight today?");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Yes, I want to book a flight.");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> dropDownInfo());
        buttons.add(button1);
        buttons.add(button2);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(label);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(buttons);
        panel.updateUI();
        panel.setVisible(true);
        frame.add(panel);
    }

    public static void dropDownInfo() {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel();
        label.setText("Choose a flight from the drop down menu");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComboBox options = new JComboBox();
        try {
            socketWriter.write("AVAIL");
            socketWriter.newLine();
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResponseListener rl = new ResponseListener(socket);
            Thread responses = new Thread(rl);
            responses.start();
            responses.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String info : flightsAvailable) {
            if (info.contains(" ") && info.substring(0, info.indexOf(" ")).equalsIgnoreCase("Alaska")) {
                options.addItem("Alaska");
            } else if (info.contains(" ") && info.substring(0,
                    info.indexOf(" ")).equalsIgnoreCase("Delta")) {
                options.addItem("Delta");
            } else if (info.contains(" ") && info.substring(0,
                    info.indexOf(" ")).equalsIgnoreCase("Southwest")) {
                options.addItem("Southwest");
            }
        }
        options.setMaximumSize(new Dimension(200, 50));
        JLabel description = new JLabel();
        description.setSize(frame.getSize());
        description.setFont(new Font(label.getFont().getName(), Font.PLAIN, 10));
        options.addActionListener(listener -> {
            String choice;
            JComboBox getSelection = (JComboBox) listener.getSource();
            choice = (String) getSelection.getSelectedItem();
            String text = "";
            if (choice != null && choice.equals("Alaska")) {
                text = "<html><center>" + new Alaska("18000").getInfo() + "</center></html>";
            } else if (choice != null && choice.equals("Delta")) {
                text = "<html><center>" + new Delta("18000").getInfo() + "<br></center></html>";
            } else if (choice != null && choice.equals("Southwest")) {
                text = "<html><center>" + new Southwest("18000").getInfo() + "<br><br></center></html>";
            }
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '.') {
                    text = text.substring(0, i + 1) + "<br>" + text.substring(i + 1);
                }
            }
            description.setText(text);
            panel.requestFocus();
        });
        options.setSelectedIndex(0);
        String choice = (String) options.getSelectedItem();
        String text = "";
        if (choice != null && choice.equals("Alaska")) {
            text = "<html><center>" + new Alaska("18000").getInfo() + "</center></html>";
        } else if (choice != null && choice.equals("Delta")) {
            text = "<html><center>" + new Delta("18000").getInfo() + "<br></center></html>";
        } else if (choice != null && choice.equals("Southwest")) {
            text = "<html><center>" + new Southwest("18000").getInfo() + "<br><br></center></html>";
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') {
                text = text.substring(0, i + 1) + "<br>" + text.substring(i + 1);
            }
        }
        description.setText(text);
        panel.requestFocus();
        JPanel info = new JPanel();
        info.add(description);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Choose this flight");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> {
            String airline = (String) options.getSelectedItem();
            try {
                socketWriter.write("PASS " + airline);
                socketWriter.newLine();
                socketWriter.flush();
            } catch (IOException f) {
                f.printStackTrace();
            }
            try {
                ResponseListener rl = new ResponseListener(socket);
                Thread responses = new Thread(rl);
                responses.start();
                responses.join();
            } catch (InterruptedException k) {
                k.printStackTrace();
            }
            int numOfPassengers = passengerList.size();
            if (numOfPassengers < capacity) {
                confirmFlight((String) options.getSelectedItem());
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! There are no more seats" +
                        " available. Please select another flight.", "Unavailable", JOptionPane.ERROR_MESSAGE);
                options.remove(options.getSelectedIndex());
            }
        });
        buttons.add(button1);
        buttons.add(button2);
        panel.requestFocus();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer((String) options.getSelectedItem());
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(label);
        panel.add(new JLabel("         "));
        panel.add(options);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(info);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(Box.createVerticalGlue());
        panel.add(buttons);
        panel.add(Box.createVerticalGlue());
        panel.updateUI();
        panel.setVisible(true);
        frame.add(panel);
    }

    public static void confirmFlight(String flightName) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel inner = new JPanel();
        JLabel label = new JLabel();
        label.setText("<html><center>Are you sure you want to book a flight on<br>" + flightName + " Airlines?" +
                "</center></html>");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        inner.add(label);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("No, I want a different flight.");
        JButton button3 = new JButton("Yes, I want this flight.");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> dropDownInfo());
        button3.addActionListener(e -> passengerInfo(flightName));
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        panel.requestFocus();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer(flightName);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(inner);
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(new JLabel("         "));
        panel.add(buttons);
        panel.updateUI();
        panel.setVisible(true);
        frame.add(panel);
    }

    public static void passengerInfo(String flightName) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel inner = new JPanel();
        JLabel label = new JLabel();
        label.setText("<html><center>Please input your information below.</center></html>");
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        inner.add(label);
        JPanel questions = new JPanel();
        questions.setLayout(new BoxLayout(questions, BoxLayout.PAGE_AXIS));
        JLabel question1 = new JLabel("What is your first name?", SwingConstants.LEFT);
        question1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextArea firstName = new JTextArea();
        firstName.setRows(5);
        firstName.setMaximumSize(new Dimension(frame.getWidth(), 150));
        firstName.setEditable(true);
        firstName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                String text = firstName.getText();
                if (text.contains("\\")) {
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == '\\') {
                            text = text.substring(0, i) + text.substring(i + 1);
                        }
                    }
                    firstName.setText(text);
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer(flightName);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        firstName.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel question2 = new JLabel("What is your last name?", SwingConstants.LEFT);
        question2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextArea lastName = new JTextArea();
        lastName.setRows(5);
        lastName.setMaximumSize(new Dimension(frame.getWidth(), 150));
        lastName.setEditable(true);
        lastName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                String text = lastName.getText();
                if (text.contains("\\")) {
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == '\\') {
                            text = text.substring(0, i) + text.substring(i + 1);
                        }
                    }
                    lastName.setText(text);
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer(flightName);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        lastName.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel question3 = new JLabel("What is your age?", SwingConstants.LEFT);
        question3.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextArea age = new JTextArea();
        age.setRows(5);
        age.setMaximumSize(new Dimension(frame.getWidth(), 150));
        age.setEditable(true);
        age.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                String text = age.getText();
                if (text.contains("\\")) {
                    for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == '\\') {
                            text = text.substring(0, i) + text.substring(i + 1);
                        }
                    }
                    age.setText(text);
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer(flightName);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        age.setAlignmentX(Component.LEFT_ALIGNMENT);
        questions.add(question1);
        questions.add(firstName);
        questions.add(new JLabel("   "));
        questions.add(question2);
        questions.add(lastName);
        questions.add(new JLabel("   "));
        questions.add(question3);
        questions.add(age);
        questions.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Next");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> {
            String first = firstName.getText();
            String last = lastName.getText();
            String ageValue = age.getText();
            Passenger passenger;
            if (checkValidity(first, last, ageValue)) {
                int choice = JOptionPane.showConfirmDialog(null, "Are all the details you" +
                                " entered correct?\nThe passenger's name is " + first + " " + last + " and their age" +
                                " is " + ageValue + ".\nIf all the information shown is correct, select the Yes\n" +
                                "button below, otherwise, select the No button.", "Confirm Passenger Information",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    passenger = new Passenger(first, last, Integer.parseInt(ageValue));
                    try {
                        socketWriter.write("ADD " + flightName);
                        socketWriter.newLine();
                        socketWriter.flush();
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(passenger);
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                    try {
                        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        socketWriter.write("FLIGHT " + flightName);
                        socketWriter.newLine();
                        socketWriter.flush();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                    try {
                        ResponseListener rl = new ResponseListener(socket);
                        Thread responses = new Thread(rl);
                        responses.start();
                        responses.join();
                    } catch (InterruptedException f) {
                        f.printStackTrace();
                    }
                    finalConfirmation(flightName, passenger);
                }
            }
        });
        buttons.add(button1);
        buttons.add(button2);
        panel.requestFocus();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    passengerInfoServer(flightName);
                }
            }
        });
        panel.add(new JLabel("         "));
        panel.add(inner);
        panel.add(questions);
        panel.add(buttons);
        panel.updateUI();
        panel.setVisible(true);
        frame.add(panel);
    }

    public static boolean checkValidity(String firstName, String lastName, String age) {
        boolean valid = true;
        if (firstName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a FIRST NAME.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else {
            for (int i = 0; i < firstName.length(); i++) {
                if (!Character.isLetter(firstName.charAt(i)) && firstName.charAt(i) != '-') {
                    JOptionPane.showMessageDialog(null, "Please enter a valid FIRST NAME " +
                            "that\nonly consists of letters or a dash(-).", "Error", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                    break;
                }
            }
        }
        if (lastName.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a LAST NAME.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else {
            for (int i = 0; i < lastName.length(); i++) {
                if (!Character.isLetter(lastName.charAt(i)) && lastName.charAt(i) != '-') {
                    JOptionPane.showMessageDialog(null, "Please enter a valid LAST NAME " +
                            "that\nonly consists of letters or a dash(-).", "Error", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                    break;
                }
            }
        }
        if (age.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter an AGE.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else {
            try {
                int ageInt = Integer.parseInt(age);
                if (ageInt < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid AGE.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid AGE.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                valid = false;
            }
        }
        return valid;
    }

    public static void passengerInfoServer(String flightName) {
        encapsulate.removeAll();
        JPanel cp = (JPanel) popUp.getContentPane();
        ActionMap aMap = cp.getActionMap();
        InputMap inMap = cp.getInputMap();
        KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        inMap.put(escKey, "closePopup");
        AbstractAction abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUp.dispose();
            }
        };
        aMap.put("closePopup", abstractAction);
        try {
            socketWriter.write("PASS " + flightName);
            socketWriter.newLine();
            socketWriter.flush();
        } catch (IOException f) {
            f.printStackTrace();
        }
        try {
            ResponseListener rl = new ResponseListener(socket);
            Thread responses = new Thread(rl);
            responses.start();
            responses.join();
        } catch (InterruptedException k) {
            k.printStackTrace();
        }
        int numOfPassengers = passengerList.size();
        JLabel label = new JLabel("<html><b>" + flightName + " Airlines.</b> " + numOfPassengers + ":" + capacity
                + "</html>");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea passengersDisplay = new JTextArea("");
        for (String passenger : passengerList) {
            passengersDisplay.append(passenger + "\n");
        }
        passengersDisplay.setEditable(false);
        passengersDisplay.setBackground(Color.lightGray);
        passengersDisplay.setRows(5);
        JScrollPane jsp = new JScrollPane(passengersDisplay,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel esc = new JLabel("Press esc to exit the screen");
        esc.setAlignmentX(Component.CENTER_ALIGNMENT);
        encapsulate.add(label);
        encapsulate.add(jsp);
        encapsulate.add(esc);
        encapsulate.updateUI();
        encapsulate.setVisible(true);
        popUp.add(encapsulate);
        popUp.setAlwaysOnTop(true);
        popUp.setVisible(true);
    }

    public static void finalConfirmation(String flightName, Passenger passenger) {
        panel.removeAll();
        JPanel begin = new JPanel();
        JLabel intro = new JLabel();
        intro.setText("<html><center>Flight data displaying for " + flightName + " Airlines<br>Enjoy your flight!<br>" +
                "Flight is now boarding at Gate A16</center></html>");
        intro.setFont(new Font(intro.getFont().getName(), Font.BOLD, 20));
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        begin.add(intro);
        try {
            socketWriter.write("PASS " + flightName);
            socketWriter.newLine();
            socketWriter.flush();
        } catch (IOException f) {
            f.printStackTrace();
        }
        try {
            ResponseListener rl = new ResponseListener(socket);
            Thread responses = new Thread(rl);
            responses.start();
            responses.join();
        } catch (InterruptedException k) {
            k.printStackTrace();
        }
        JTextArea passengersDisplay = new JTextArea("                                                    " +
                "                                              " + passengerList.size() + ":" + capacity + "\n");
        for (String pass : passengerList) {
            passengersDisplay.append("     " + pass + "\n");
        }
        passengersDisplay.setEditable(false);
        passengersDisplay.setBackground(Color.lightGray);
        passengersDisplay.setRows(10);
        JScrollPane jsp = new JScrollPane(passengersDisplay,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoardingPass bp = new BoardingPass(passenger, selectedAirline.getGate());
        JPanel pass = new JPanel();
        JLabel boardingPass = new JLabel(bp.toString());
        boardingPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        pass.add(boardingPass);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Refresh Flight Status");
        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University\n" +
                    "Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
            frame.dispose();
        });
        button2.addActionListener(e -> finalConfirmation(flightName, passenger));
        buttons.add(button1);
        buttons.add(button2);
        panel.add(begin);
        panel.add(jsp);
        panel.add(pass);
        panel.add(buttons);
        panel.updateUI();
        panel.setVisible(true);
        frame.add(panel);
    }

    public static void setFlightsAvailable(ArrayList<String> flightsAvailable) {
        ReservationClient.flightsAvailable = flightsAvailable;
    }

    public static void setPassengerList(ArrayList<String> passengerList) {
        ReservationClient.passengerList = passengerList;
    }

    public static void setCapacity(int capacity) {
        ReservationClient.capacity = capacity;
    }

    public static void setSelectedAirline(Airline selectedAirline) {
        ReservationClient.selectedAirline = selectedAirline;
    }
}

/**
 * ResponseListener
 * <p>
 * This class defines the ResponseListener Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
class ResponseListener implements Runnable {
    private Socket socket;

    public ResponseListener(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String command = socketReader.readLine();
            String[] a = command.split(" ");
            String type = a[0];
            switch (type) {
                case "AVAIL": {
                    ArrayList<String> flights = new ArrayList<>(0);
                    String line = socketReader.readLine();
                    while (!line.equals("END")) {
                        flights.add(line);
                        line = socketReader.readLine();
                    }
                    ReservationClient.setFlightsAvailable(flights);
                    break;
                }
                case "PASS": {
                    ReservationClient.setCapacity(Integer.parseInt(socketReader.readLine()));
                    ArrayList<String> passengers = new ArrayList<>(0);
                    String line = socketReader.readLine();
                    while (!line.equals("END")) {
                        passengers.add(line);
                        line = socketReader.readLine();
                    }
                    ReservationClient.setPassengerList(passengers);
                    break;
                }
                case "FLIGHT":
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Airline airline = (Airline) ois.readObject();
                    ReservationClient.setSelectedAirline(airline);
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}