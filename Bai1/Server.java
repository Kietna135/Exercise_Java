package Bai1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server da sang san cho ket noi voi client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client ket noi: " + clientSocket.getInetAddress().getHostAddress());

                // Tạo một luồng xử lý riêng cho từng client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Gửi các số từ 1 đến 1000 đến client
                for (int i = 1; i <= 1000; i++) {
                    out.println(i);
                    Thread.sleep(1000); // Đợi 1 giây trước khi gửi số tiếp theo
                }

                out.close();
                clientSocket.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
