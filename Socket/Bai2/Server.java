package Bai2;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo socket server và lắng nghe trên cổng 8888
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server hãy nhập tin nhắn khi có kết nối...");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Đã có một kết nối mới từ client: " + clientSocket.getInetAddress().getHostAddress());

                // Tạo luồng đầu vào từ client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Tạo luồng đầu ra tới client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;

                // Đọc dữ liệu từ client và gửi lại thời gian nếu nhận được "time"
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equals("time")) {
                        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                        out.println(time);
                    }
                }

                // Đóng kết nối với client
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}