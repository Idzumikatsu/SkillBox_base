package org.example;

public class Link {

    String link;
    int depth;
    boolean isFinal;

    public Link(String link, int depth, boolean isFinal) {
        this.link = link;
        this.depth = depth;
        this.isFinal = isFinal;
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

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        return "Link{" +
                "link='" + link + '\'' +
                ", depth=" + depth +
                ", isFinal=" + isFinal +
                '}';
    }
}
