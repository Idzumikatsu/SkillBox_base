package WebPageParser;

public record Line(String number, String line) {

    @Override
    public String toString() {
        return "WebPageParser.Line{" +
                "number=" + number +
                ", lineName=" + line +
                '}';
    }
}
