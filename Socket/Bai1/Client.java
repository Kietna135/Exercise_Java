import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối tới server
            Socket socket = new Socket("localhost", 8888);

            // Tạo luồng đầu vào từ bàn phím
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Tạo luồng đầu ra tới server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Tạo luồng đầu vào từ server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;

            // Đọc dữ liệu từ bàn phím và gửi tới server
            while ((inputLine = userInput.readLine()) != null) {
                out.println(inputLine);

                // Đọc dữ liệu từ server và in ra màn hình
                String response = in.readLine();
                System.out.println("Server: " + response);
            }

            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}