import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Graphics {

    static JFrame frame;
    static JPanel panel;
    static JFrame popUp;
    static JPanel encapsulate;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                    frame = new JFrame("Purdue University Flight Reservation System");
                    frame.setSize(700, 500);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setResizable(false);
                    panel = new JPanel();
                    welcome();
                    frame.setVisible(true);
                }
        );
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
        button1.addActionListener(e -> frame.dispose());
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
        button1.addActionListener(e -> frame.dispose());
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
        //TODO Make sure that these options are dependent on information from the server about which flights are still available
        options.addItem("Alaska");
        options.addItem("Delta");
        options.addItem("Southwest");
        options.setMaximumSize(new Dimension(200, 50));
        JLabel description = new JLabel();
        description.setSize(frame.getSize());
        description.setFont(new Font(label.getFont().getName(), Font.PLAIN, 10));
        description.setText(new Alaska("18000").getInfo());
        options.addItemListener(listener -> {
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
        String text = "<html><center>" + new Alaska("18000").getInfo() + "</center></html>";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') {
                text = text.substring(0, i + 1) + "<br>" + text.substring(i + 1);
            }
        }
        description.setText(text);
        JPanel info = new JPanel();
        info.add(description);
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button1 = new JButton("Exit");
        JButton button2 = new JButton("Choose this flight");
        button1.addActionListener(e -> frame.dispose());
        button2.addActionListener(e -> frame.setSize(300, 400));
        buttons.add(button1);
        buttons.add(button2);
        panel.requestFocus();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_SLASH) {
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

    public static void passengerInfoServer(String airline) {
        popUp = new JFrame();
        popUp.setResizable(false);
        popUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popUp.setSize(200, 200);
        encapsulate = new JPanel();
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
        //TODO ask for server to respond with capacity and list of passengers
        /*
            int capacity = getCapacity();
            ArrayList<String> passengers = flightPassengers();
         */
        int capacity = 100;
        ArrayList<String> passengers = new ArrayList<>(0);
        passengers.add("A. NARAIN, 20");
        passengers.add("K. ABHYANKAR, 19");
        passengers.add("N. CLAYMAN, 19");
        passengers.add("A. NARAIN, 20");
        passengers.add("K. ABHYANKAR, 19");
        passengers.add("N. CLAYMAN, 19");
        passengers.add("A. NARAIN, 20");
        passengers.add("K. ABHYANKAR, 19");
        passengers.add("N. CLAYMAN, 19");
        JLabel label = new JLabel("<html><b>" + airline + " Airlines.</b> " + passengers.size() + ":" + capacity
                + "</html>");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea passengersDisplay = new JTextArea("");
        for (String passenger : passengers) {
            passengersDisplay.append(passenger + "\n");
        }
        passengersDisplay.setEditable(false);
        passengersDisplay.setRows(5);
        JScrollPane jsp = new JScrollPane(passengersDisplay,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel esc = new JLabel("Press esc to exit the screen");
        esc.setAlignmentX(Component.CENTER_ALIGNMENT);
        encapsulate.add(label);
        encapsulate.add(jsp);
        encapsulate.add(esc);
        encapsulate.setVisible(true);
        popUp.add(encapsulate);
        popUp.setVisible(true);
    }



}
