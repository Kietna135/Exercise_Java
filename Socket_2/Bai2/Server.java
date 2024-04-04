package Bai2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            System.out.println("Server đã khởi động. Đang chờ kết nối từ các client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client đã kết nối: " + clientSocket.getInetAddress().getHostAddress());

                // Tạo một luồng xử lý riêng cho từng client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private DataOutputStream out;
        private String username;

        public ClientHandler(Socket clientSocket) throws Exception {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                // Nhận tên người dùng từ client
                username = in.readLine();
                broadcastMessage(username + " đa tham gia nhom chat.");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // Gửi tin nhắn từ client đến tất cả các client khác
                    broadcastMessage(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
                clients.remove(this);
                broadcastMessage(username + " đa rời khoi nhom chat.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            try {
                out.writeBytes(message + "\n");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
