package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Writer {
    private static final String DST_FOLDER = "src/main/resources/map/";
    private static final String FILE_TYPE = "txt";
    private static String fileName;

    public void writeToFile(String string, String initialURL) throws MalformedURLException {

        fileName = initialURL
                .replace(".", "_")
                .replace("https://","");

        if (!Files.exists(Paths.get(DST_FOLDER))) {
            new File(DST_FOLDER).mkdir();
        }
        String filePath = DST_FOLDER.concat(fileName).concat(".").concat(FILE_TYPE);
        File file = new File(filePath);

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer.write(string);
        writer.flush();
    }
}
