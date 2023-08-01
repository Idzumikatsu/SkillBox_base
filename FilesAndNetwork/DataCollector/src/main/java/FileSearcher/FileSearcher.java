package FileSearcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileSearcher {

    static List<String> searchResult = new ArrayList<>();

    public static List<String> searchFiles(String path) {
        File file = new File(path);

        if (isTarget(file)){
            searchResult.add(file.getPath());
        }

        if (file.isDirectory()) {
            File[] listOfPaths = file.listFiles();
            Arrays.stream(Objects.requireNonNull(listOfPaths)).forEachOrdered(FileSearcher::accept);
        }
        return searchResult;
    }

    private static boolean isTarget (File file) {
        return file.isFile() && (file.getAbsolutePath().endsWith(".csv") || file.getAbsolutePath().endsWith(".json"));
    }

    private static void accept(File filePath) {
        if (isTarget(filePath)) {
            searchResult.add(filePath.getPath());
        } else {
            searchFiles(filePath.getPath());
        }
    }
}
