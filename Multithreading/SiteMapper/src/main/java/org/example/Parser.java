package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Parser implements Comparable<Element> {

    private Set<Link> set = new LinkedHashSet<>();

    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get();
    }

    public List<Link> parseLinks(Document doc, String attrKey, String URL) {
        Elements links = doc.select("a[href]");
        List<Link> list = new ArrayList<>(
                links.stream()
                .filter(el -> el.attr(attrKey).contains(URL))
                .sorted(Comparator.comparing(e -> e.attr(attrKey)))
                .map(e -> new Link(e.attr(attrKey), calcDepth(e, attrKey), isFinal(e, attrKey)))
                .sorted(Comparator.comparing(Link::getDepth))
                .toList());

        set.addAll(list);
        list.clear();
        list.addAll(set);

        return list;


    }

    private int calcDepth(Element e, String attrKey) {
        return e.attr(attrKey).split("/").length - 3;
    }

    private boolean isFinal(Element e, String attrKey) {
        return !e.attr(attrKey).endsWith("/");
    }

    @Override
    public int compareTo(Element o) {
        return this.compareTo(o);
    }
}
