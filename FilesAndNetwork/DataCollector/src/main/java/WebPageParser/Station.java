package WebPageParser;

public record Station(String name, String number) {

    @Override
    public String toString() {
        return "WebPageParser.WebPageParser.Station{" +
                "line=" + name +
                ", number=" + number +
                '}';
    }
}
