package JSONParser;

import FileSearcher.FileSearcher;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONParser {
    private static final List<String> FILE_PATHS = FileSearcher.searchFiles("data/");
    private static List<String> correctPaths = new ArrayList<>();
    private static final List<Depth> depths = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Depth> parseJSONtoDepth() throws IOException {
        correctPaths = checkFilePaths(FILE_PATHS);
        for (String path : correctPaths) {
            Depth[] d = objectMapper.readValue(new File(path), Depth[].class);
            depths.addAll(Arrays.stream(d).toList());
        }
        return depths;
    }

    private static List<String> checkFilePaths(List<String> filePaths) {
        for (String path : filePaths) {
            if (path.endsWith(".json")) {
                correctPaths.add(path);
            }
        }
        return correctPaths;
    }

}
