import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * ReservationServer
 * <p>
 * This class defines the ReservationServer Object
 *
 * @author Satabdiya Roy, Abbey Brashear, lab sec LC2
 * @version December 3, 2019
 */
public final class ReservationServer {
    private ServerSocket serverSocket;
    private Set<String> reservations;
    private ClientHandler clientHandler;
    private static Alaska alaska;
    private static Delta delta;
    private static Southwest southwest;

    public static void main(String[] args) {
        ReservationServer server;
        try {
            server = new ReservationServer();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.serveClients();
    }

    public ReservationServer() throws IOException {
        alaska = new Alaska("18000");
        delta = new Delta("18000");
        southwest = new Southwest("18000");
        this.serverSocket = new ServerSocket(0);
        this.reservations = new HashSet<>();
    }

    public void serveClients() {
        Socket clientSocket;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            }
            connectionCount++;
            System.out.printf("<Client %d connected...>%n", connectionCount);
            clientHandler = new ClientHandler(clientSocket);
            Thread clientRequests = new Thread(clientHandler);
            clientRequests.start();
        }
    }

    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.serverSocket);

        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ReservationServer) {
            return Objects.equals(this.serverSocket, ((ReservationServer) object).serverSocket);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String format = "ReservationServer[%s]";

        return String.format(format, this.serverSocket);
    }

    public static Alaska getAlaska() {
        return alaska;
    }

    public static Delta getDelta() {
        return delta;
    }

    public static Southwest getSouthwest() {
        return southwest;
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private File file;
    PrintWriter socketWriter;
    Scanner socketReader;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.file = new File("reservations.txt");
        try {
            socketWriter = new PrintWriter(clientSocket.getOutputStream());
            socketReader = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (socketReader.hasNextLine()) {
            String command = socketReader.nextLine();
            String[] a = command.split(" ");
            String type = a[0];
            switch (type) {
                case "AVAIL":
                    socketWriter.println(command);
                    socketWriter.flush();
                    getFlightInfo();
                    break;
                case "PASS":
                    socketWriter.println(command);
                    socketWriter.flush();
                    String airline = a[1];
                    if (airline.equalsIgnoreCase("Alaska")) {
                        socketWriter.println(new Alaska("18000").getCapacity());
                        socketWriter.flush();
                        getPassengers("Alaska");
                    } else if (airline.equalsIgnoreCase("Delta")) {
                        socketWriter.println(new Delta("18000").getCapacity());
                        socketWriter.flush();
                        getPassengers("Delta");
                    } else if (airline.equalsIgnoreCase("Southwest")) {
                        socketWriter.println(new Southwest("18000").getCapacity());
                        socketWriter.flush();
                        getPassengers("Southwest");
                    }
                    socketWriter.println("END");
                    socketWriter.flush();
                    break;
                case "ADD":
                    addPassenger(a[1]);
                    break;
                case "FLIGHT":
                    socketWriter.println(command);
                    socketWriter.flush();
                    getFlightObject(a[1]);
                    break;
            }
        }
        socketReader.close();
        socketWriter.close();
    }


    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.clientSocket);

        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ClientHandler) {
            return Objects.equals(this.clientSocket, ((ClientHandler) object).clientSocket);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("ClientHandler[%s]", this.clientSocket);
    }

    public synchronized void getFlightInfo() {
        try {
            Scanner reader = new Scanner(file);
            String line;
            int passCount;
            int capacity;
            int index;
            ArrayList<String> fileText = new ArrayList<>(0);
            while (true) {
                line = reader.nextLine();
                if (line.equals("EOF")) {
                    fileText.add(line);
                    break;
                }
                fileText.add(line);
            }
            for (int i = 0; i < fileText.size(); i++) {
                if (fileText.get(i).equals("ALASKA") || fileText.get(i).equals("DELTA") ||
                        fileText.get(i).equals("SOUTHWEST")) {
                    i++;
                    index = fileText.get(i).indexOf("/");
                    passCount = Integer.parseInt(fileText.get(i).substring(0, index));
                    capacity = Integer.parseInt(fileText.get(i).substring(index + 1));
                    if (passCount < capacity) {
                        socketWriter.println(fileText.get(i - 1) + " " + fileText.get(i));
                        socketWriter.flush();
                    }
                }
            }
            socketWriter.println("END");
            socketWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addPassenger(String airline) {
        try {
            Scanner reader = new Scanner(file);
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Passenger passenger;
            try {
                passenger = (Passenger) ois.readObject();
                String line;
                ArrayList<String> fileText = new ArrayList<>(0);
                while (true) {
                    line = reader.nextLine();
                    if (line.equals("EOF")) {
                        fileText.add(line);
                        break;
                    }
                    fileText.add(line);
                }

                for (int i = 0; i < fileText.size(); i++) {
                    if (fileText.get(i).contains(airline)) {
                        i++;
                        fileText.add(i, passenger.stringToAddToFile());
                        String numbers = fileText.get(i - 2);
                        int passengers = Integer.parseInt(numbers.substring(0, numbers.indexOf("/")));
                        fileText.set(i - 2, ++passengers + numbers.substring(numbers.indexOf("/")));
                    }
                }
                PrintWriter writer = new PrintWriter(file);

                for (String s : fileText) {
                    writer.println(s);
                    writer.flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getPassengers(String airline) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String nextLine = reader.nextLine();
                if (nextLine.equals(airline + " passenger list")) {
                    if (reader.hasNextLine()) {
                        nextLine = reader.nextLine();
                        while (!nextLine.equals("")) {
                            socketWriter.println(nextLine);
                            socketWriter.flush();
                            nextLine = reader.nextLine();
                        }
                        socketWriter.flush();
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getFlightObject(String airline) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            if (airline.equalsIgnoreCase("Alaska")) {
                oos.writeObject(ReservationServer.getAlaska());
            } else if (airline.equalsIgnoreCase("Delta")) {
                oos.writeObject(ReservationServer.getDelta());
            } else if (airline.equalsIgnoreCase("Southwest")) {
                oos.writeObject(ReservationServer.getSouthwest());
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
    }
}