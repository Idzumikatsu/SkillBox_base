package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Parser implements Comparable<Element> {

    public Document getDocument(String url) throws IOException, InterruptedException {
        Thread.sleep(150);
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(0)
                .userAgent("Mozilla/5.0")
                .get();
    }

    public Set<Link> parseLinks(Document doc, String attrKey, String URL) {
        Elements links = doc.select("a[href]");
        Set<Link> linkSet = new LinkedHashSet<>(
                links.stream()
                .filter(el ->
                        !el.absUrl(attrKey).isEmpty()
                        && el.absUrl(attrKey).startsWith(URL)
                        && !el.absUrl(attrKey).contains("#")
                )
                .sorted(Comparator.comparing(e -> e.absUrl(attrKey)))
                .map(e -> new Link(e.absUrl(attrKey), calcDepth(e, attrKey)))
                .toList());

        System.out.println("link parsed " + URL);
        return linkSet;
    }

    private int calcDepth(Element e, String attrKey) {
        return e.absUrl(attrKey).split("/").length - 3;
    }

    @Override
    public int compareTo(Element o) {
        return this.compareTo(o);
    }
}
