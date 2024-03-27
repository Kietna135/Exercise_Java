package Bai2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Clock extends JFrame {
    private JLabel timeLabel;

    public Clock() {
        // Thiết lập giao diện đồng hồ
        setTitle("Clock");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(timeLabel);

        // Tạo timer để gửi yêu cầu "time" đến server mỗi giây một lần
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();

        setVisible(true);
    }

    private void updateTime() {
        try {
            // Kết nối tới server
            Socket socket = new Socket("localhost", 8888);

            // Tạo luồng đầu vào từ server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Tạo luồng đầu ra tới server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Gửi yêu cầu "time" đến server
            out.println("time");

            // Đọc thời gian từ server
            String time = in.readLine();

            // Cập nhật thời gian lên giao diện đồng hồ
            timeLabel.setText(time);

            // Đóng kết nối với server
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Clock();
            }
        });
    }
}