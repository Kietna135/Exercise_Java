package Iostream;

import java.io.File;

public class Bai1 {
    public static void main(String[] args) {
        String filePath = getInputFilePath();

        File file = new File(filePath);

        if (file.exists()) {
            long fileSize = file.length();

            String formattedFileSize = formatFileSize(fileSize);

            System.out.println("Do lon cua tep la: " + formattedFileSize);
        } else {
            System.out.println("Tep khong ton tai.");
        }
    }

    private static String getInputFilePath() {
        return "E:\\Java_Socket\\Iostream\\Lập trình java nâng cao.txt";
    }

    private static String formatFileSize(long fileSize) {
        final long kiloBytes = 1024;
        final long megaBytes = kiloBytes * 1024;
        final long gigaBytes = megaBytes * 1024;

        if (fileSize < kiloBytes) {
            return fileSize + " bytes";
        } else if (fileSize < megaBytes) {
            return fileSize / kiloBytes + " KB";
        } else if (fileSize < gigaBytes) {
            return fileSize / megaBytes + " MB";
        } else {
            return fileSize / gigaBytes + " GB";
        }
    }
}