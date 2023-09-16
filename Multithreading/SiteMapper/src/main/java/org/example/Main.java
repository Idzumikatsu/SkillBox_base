package org.example;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String URL = "https://skillbox.ru";
    private static final String ATTR_KEY = "href";


    public static void main(String[] args) throws IOException {

        Parser parser = new Parser();
        Document doc = parser.getDocument(URL);

        List<Link> list = parser.parseLinks(doc, ATTR_KEY, URL);

    }
}