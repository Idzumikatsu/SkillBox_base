package org.example;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class RecursiveParserTask extends RecursiveTask<Set<Link>> {

    private String URL;
    private static final String ATTR_KEY = "href";
    private Set<Link> resultset = new LinkedHashSet<>();
    private Set<String> processedLinks;

    public RecursiveParserTask(String URL, Set<String> processedLinks) {
        this.URL = URL;
        this.processedLinks = processedLinks;
    }

    @Override
    protected Set<Link> compute() {

        try {
            Parser parser = new Parser();
            Document doc = parser.getDocument(URL);
            Set<Link> set = parser.parseLinks(doc, ATTR_KEY, URL);
            resultset.addAll(set);

            List<RecursiveParserTask> taskList = new ArrayList<>();

            for (Link link : set) {
                if (processedLinks.contains(link.getLink())) {
                    continue;
                }
                RecursiveParserTask task = new RecursiveParserTask(link.getLink(), processedLinks);
                task.fork();
                taskList.add(task);
                processedLinks.add(link.getLink());
                System.out.println("task forked " + task.URL);
            }

            for (RecursiveParserTask task : taskList) {
                resultset.addAll(task.join());
                System.out.println("task joined " + task.URL);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultset;
    }
}
