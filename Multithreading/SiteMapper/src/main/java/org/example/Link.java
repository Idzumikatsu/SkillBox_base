package org.example;

import java.util.Objects;

public class Link {

    private String link;
    private int depth;

    public Link(String link, int depth) {
        this.link = link;
        this.depth = depth;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Link link1 = (Link) object;
        return depth == link1.depth && Objects.equals(link, link1.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, depth);
    }

    @Override
    public String toString() {
        return "Link{" +
                "link='" + link + '\'' +
                ", depth=" + depth +
                '}';
    }
}
