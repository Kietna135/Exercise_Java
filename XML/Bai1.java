package XML;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class Bai1 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Nhap duong dan thu muc: ");
            String directoryPath = reader.readLine();

            File directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                System.out.println("Duong dan thu muc khong hop le.");
                return;
            }

            System.out.println("<root>");
            listDirectory(directory, 1);
            System.out.println("</root>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(File directory, int level) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    printIndentation(level);
                    System.out.println("<" + file.getName() + ">");
                    listDirectory(file, level + 1);
                    printIndentation(level);
                    System.out.println("</" + file.getName() + ">");
                } else {
                    printIndentation(level);
                    System.out.println("<file>" + file.getName() + "</file>");
                }
            }
        }
    }

    private static void printIndentation(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    "); 
        }
    }
}