package org.example;

import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String initialURL = "https://skillbox.ru";
    private static Set<String> processedLinks = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws MalformedURLException {

        RecursiveParserTask task = new RecursiveParserTask(initialURL, processedLinks);
        Set<Link> resultSet = new ForkJoinPool().invoke(task);

        List<Link> list = new ArrayList<>(resultSet);
        list = list.stream().sorted(Comparator.comparing(Link::getLink)).toList();

        StringBuilder sb = new StringBuilder();

        for (Link link : list) {
            sb.append("\t".repeat(Math.max(0, link.getDepth())))
                    .append(link.getLink()).append("\n");
        }

        Writer writer = new Writer();
        writer.writeToFile(sb.toString(), initialURL);

    }
}