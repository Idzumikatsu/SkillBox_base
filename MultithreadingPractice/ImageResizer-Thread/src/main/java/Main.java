
import java.io.File;

public class Main {

    private static final int NEW_WIDTH = 300;

    public static void main(String[] args) {

        String srcFolder = "C:\\Downloads\\pics";
        String dstFolder = "C:\\Downloads\\resized-pics";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int middle = files.length >> 1;

        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, 0, files1.length);
        ImageResizer resizer1 = new ImageResizer(files1, NEW_WIDTH, dstFolder, start);
        resizer1.start();

        File[] files2 = new File[files.length - middle];
        System.arraycopy(files, middle, files2, 0, files2.length);
        ImageResizer resizer2 = new ImageResizer(files2, NEW_WIDTH, dstFolder, start);
        resizer2.start();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}