
import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int NEW_WIDTH = 300;

    public static void main(String[] args) {

        String srcFolder = "C:\\Downloads\\pics";
        String dstFolder = "C:\\Downloads\\resized-pics";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        List<File> fileList = Arrays.stream(files).toList();

        int cores = Runtime.getRuntime().availableProcessors();

        List<List<File>> splittedList = Lists.partition(fileList, fileList.size() / cores);

        splittedList.forEach(sl ->
            new ImageResizer(sl, NEW_WIDTH, dstFolder, start).start()
        );
    }
}