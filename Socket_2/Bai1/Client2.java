package Bai1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String number;
            while ((number = in.readLine()) != null) {
                System.out.println("Received number: " + number);
            }

            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
