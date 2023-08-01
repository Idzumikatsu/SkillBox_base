package WebPageParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebPageParser {

    private List<Line> lines;
    private List<Station> stations;
    private List<Connection> connections;

    public Document parse(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        this.lines = parseLines(doc);
        this.stations = parseStations(doc);
        this.connections = parseConnections(doc);
        return doc;
    }

    private List<Line> parseLines(Document doc) {
        List<String> lineNames = doc.select("span[^data-line]").eachText();
        List<String> lineNumbers = doc.select("span[^data-line]").eachAttr("data-line");
        List<Line> lines = new ArrayList<>();
        for (String name : lineNames) {
            String numberIndex = lineNumbers.get(lineNames.indexOf(name));
            lines.add(new Line(numberIndex, name));
        }
        return lines;
    }

    private List<Station> parseStations(Document doc) {
        String pattern = "div[data-line=\"%s\"] > p";
        List<String> lineNumbers = doc.select("span[^data-line]").eachAttr("data-line");
        List<Station> stations = new ArrayList<>();
        for (String number : lineNumbers) {
            List<String> stationNames = doc.select(String.format(pattern, number)).eachText();
            stationNames.stream()
                    .map(name -> name.substring(name.indexOf(" ")).strip())
                    .map(name -> new Station(name, number))
                    .forEach(stations::add);
        }
        return stations;
    }

    private List<Connection> parseConnections(Document doc) {
        List<String> connectedStationsNames = doc.select(".single-station:has(span[title])").eachText();
        List<Connection> connections = new ArrayList<>();
        for (String connection : connectedStationsNames) {
            String name = connection.substring(connection.indexOf(" ")).strip();
            connections.add(new Connection(name, true));
        }
        return connections;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
