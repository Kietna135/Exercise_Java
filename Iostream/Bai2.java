package Iostream;

import java.io.File;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        String path = getInputPath();

        File file = new File(path);

        if (file.exists()) {
            deleteFile(file);
        } else {
            System.out.println("Thu muc khong ton tai");
        }
    }

    private static String getInputPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap duong dan cho thu muc: ");
        return scanner.nextLine();
    }

    private static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    deleteFile(subFile);
                }
            }
        }

        if (file.delete()) {
            System.out.println("Da xoa thanh cong: " + file.getAbsolutePath());
        } else {
            System.out.println("Khong the xoa: " + file.getAbsolutePath());
        }
    }
}