package CSVParser;

import FileSearcher.FileSearcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    private static final List<String> FILE_PATHS = FileSearcher.searchFiles("data/");
    private static List<String> correctPaths = new ArrayList<>();
    private static final List<Date> dates = new ArrayList<>();

    private static List<String> readFile(String path) {
        List<String> data = new ArrayList<>();
        try {
            data = Files.readAllLines(Path.of(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Date> parseCSVtoDate() {
        correctPaths = checkFilePaths(FILE_PATHS);
        for (String path : correctPaths) {
            List<String> fileData = readFile(path);
            for (String data : fileData) {
                String[] arr = data.split(",");
                dates.add(new Date(arr[0].strip(), arr[1].strip()));
            }
        }
        return dates;
    }

    private static List<String> checkFilePaths (List<String> filePaths) {
        for (String path : filePaths) {
            if (path.endsWith(".csv")) {
                correctPaths.add(path);
            }
        }
        return correctPaths;
    }
}
