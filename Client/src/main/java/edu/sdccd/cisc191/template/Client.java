package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

/*
Connects to server and expects an object of city and state values from Table before converting to string, formatting,
and printing out to console
 */
public class Client {
    private static Socket clientSocket;

    // Start client socket and connection to server
    public static void startConnection(String ip, int port, String[] args) throws IOException {
        clientSocket = new Socket(ip, port);
        // call start application and print location received from application
        try {
            startApplication(args);
            System.out.println(objectToString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Start application
    public static void startApplication(String[] args) throws NullPointerException {
        Table.main(args);
    }

    // Convert object to string and format
    public static String objectToString() {
        // get object and convert to string
        String location = Table.getLocation();

        // split string by comma into city and state variables
        String city = location.substring(0, location.indexOf(","));
        String state = location.substring(location.indexOf(",") + 1);
        // add line break between city and state
        location = city + "\n" + state;
        // locate all = char and replace with :
        while (location.contains("=")) {
            location = location.substring(0, location.indexOf("=")) + ": " + location.substring(location.indexOf("=") + 1);
        }
        return location;
    }

    // Close client socket
    public void stopConnection() throws IOException {
        clientSocket.close();
    }


    public static void main(String[] args) {
        Client client = new Client();
        try {
            startConnection("127.0.0.1", 4444, args);
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

