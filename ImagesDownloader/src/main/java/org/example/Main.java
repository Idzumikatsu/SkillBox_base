package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static java.nio.file.Files.newOutputStream;


public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://giknutye.ru/pokemon-anime-poryadok-prosmotra-hronologiya/";
        Document doc = Jsoup.connect(url).get();

        HashSet<String> links;
        Elements images = doc.select("img");

        links = images.stream().map(image -> image.attr("abs:src")).collect(Collectors.toCollection(HashSet::new));

        int number = 1;
        for (String link : links) {
            String extension = link
                    .replaceAll("^.+\\.", "")
                    .replace("?.+$", "");
            String filePath = "data/" + number++ + "." + extension;
            try {
                download(link,filePath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void download(String link, String filePath) throws IOException {

        URLConnection connection = new URL(link).openConnection();
        InputStream inStream = connection.getInputStream();

        byte[] lines = inStream.readAllBytes();
        File targetFile = new File(filePath);

        if (!targetFile.exists()){
            targetFile.createNewFile();
        }

        OutputStream os = newOutputStream(targetFile.toPath());
        os.write(lines);
        os.flush();

        inStream.close();
        os.close();

    }
}