import javax.swing.*;
import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.PrintWriter;

import java.net.InetSocketAddress;

import java.net.Socket;

import javax.swing.JOptionPane;

//Designed by Mr. Nitin Kumar Chauhan on 23rd Sep 2023. Reference: ChatGPT 3.5 for Java programming.

public class TelnetTester {

    public static void main(String[] args) {
        String message =
                "Telnet Tester is a java based program, designed to help system/network/security admins to test connectivity based on their requirements.\n"
                        + "Test if the IP address is reachable from the source on the said port.\n"
                        + "It executes the command \"telnet <IP address> <port>\" and saves result.\n"
                        + "\n"
                        + "Instructions:\n"
                        + "1. Place this file at source Windows-OS system in \"C:\\telnettest\\\".\n"
                        + "2. Create an input file \"IPaddress.txt\" and save it at the above location.\n"
                        + "3. The IP and port must be in the format as \"x.x.x.x:port\". Example: 8.8.8.8:53\n"
                        + "4. Output will be saved in \"output.log\" file.\n"
                        + "5. Click \"OK\" to proceed.\n"
                        + "\n"
                        + "Program will fail if the input file is not found, or does not have IP and  port as instructed.";

                JOptionPane.showMessageDialog(null, message, "Instructions", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Please create the input file and name it as \"IPaddress.txt\" and save it at \"c:\\telnettest\\\"");
        System.out.println("The IP and port must be in format as \"x.x.x.x:port\".");

        String inputFile = "C:\\telnettest\\IPaddress.txt";  // Path to the input file

        String outputFile = "C:\\telnettest\\output.log";  // Path to the output file



        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));

             PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {



            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(":");

                if (parts.length != 2) {

                    System.err.println("Invalid input format: " + line);

                    continue;

                }



                String ipAddress = parts[0];

                int port = Integer.parseInt(parts[1]);



                if (telnet(ipAddress, port)) {

                    writer.println(ipAddress + ":" + port + " - success");

                } else {

                    writer.println(ipAddress + ":" + port + " - failed");

                }

            }



            System.out.println("Testing completed. Results written to " + outputFile);

        } catch (IOException e) {

            e.printStackTrace();


        }

        message = "Execution completed.\n"
                + "Please check the output file for results.";

        JOptionPane.showMessageDialog(null, message, "Execution status", JOptionPane.INFORMATION_MESSAGE);


    }



    private static boolean telnet(String ipAddress, int port) {

        try (Socket socket = new Socket()) {

            socket.connect(new InetSocketAddress(ipAddress, port), 1000);  // 1-second timeout

            return true;

        } catch (IOException e) {

            return false;

        }

    }

}
