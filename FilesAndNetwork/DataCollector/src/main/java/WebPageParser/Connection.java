package WebPageParser;

public record Connection( String name, Boolean hasConnection) {

    @Override
    public String toString() {
        return "Connection{" +
                "line='" + name + '\'' +
                ", hasConnection=" + hasConnection +
                '}';
    }
}
