import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo socket server và lắng nghe trên cổng 8888
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server Hay nhap tin nhan khi co ket noi...");

            // Chấp nhận kết nối từ client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Co ket noi tu client: " + clientSocket.getInetAddress().getHostAddress());

            // Tạo luồng đầu vào từ client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Tạo luồng đầu ra tới client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;

            // Đọc dữ liệu từ client và ghi ra console
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);

                // Đọc dữ liệu từ bàn phím và gửi lại cho client
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                String serverResponse = serverInput.readLine();
                out.println(serverResponse);
            }

            // Đóng kết nối
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}