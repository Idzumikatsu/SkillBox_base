import java.io.File;
import java.util.Scanner;


public class Main {
    private static int count = 0;

    public static void main(String[] args) {

        while (true) {
            System.out.println("Введите путь к файлу или папке");
            String path = new Scanner(System.in).nextLine();

            System.out.println(getHumanReadableSize(getFolderSize(path)));
            count = 0;
        }

    }

    private static long getFolderSize(String path) {
        long value = 0;

        File file = new File(path);
        if (isFile(file)) {
            return file.length();
        }

        if (isFolder(file)) {
            File[] listOfPaths = file.listFiles();
            for (File filePath : listOfPaths) {
                value += getFolderSize(filePath.getAbsolutePath());
            }
        }
        return value;
    }

    private static boolean isFolder(File file) {
        return file.isDirectory();
    }

    private static boolean isFile(File file) {
        return file.isFile();
    }

    private static String getHumanReadableSize(long value) {
        int result = (int) convertSize(value);
        StringBuilder sb = new StringBuilder();
        return switch (count) {
            case 1, 2 -> sb.append(result).append("kb").toString();
            case 3 -> sb.append(result).append("Mb").toString();
            case 4 -> sb.append(result).append("Gb").toString();
            default -> "Размер файлов превышает определяемый программой";
        };
    }

    private static long convertSize (long value){
        count++;
        long convertValue = value / 1024;
        return value < 1024 ? value : convertSize(convertValue);
    }


}