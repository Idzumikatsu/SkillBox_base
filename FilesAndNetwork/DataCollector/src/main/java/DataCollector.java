import CSVParser.Date;
import WebPageParser.*;
import JSONParser.*;
import CSVParser.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataCollector {
    private final String URL = "https://skillbox-java.github.io/";
    private static List<Station> stations;
    private static List<Line> lines;
    private static List<Connection> connections;
    private static List<Date> dates;
    private static List<Depth> depths;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public DataCollector() throws IOException {
        WebPageParser wbp = new WebPageParser();
        Document document = wbp.parse(URL);
        stations = wbp.getStations();
        lines = wbp.getLines();
        connections = wbp.getConnections();
        dates = CSVParser.parseCSVtoDate();
        depths = JSONParser.parseJSONtoDepth();
    }

    @JsonPropertyOrder({"name", "line", "date", "depth", "hasConnection"})
    private class StationsInfo {
        @JsonProperty(value = "name")
        private String name;
        @JsonProperty(value = "line")
        private String line;
        @JsonProperty(value = "date")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String date;
        @JsonProperty(value = "depth")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Double depth;
        @JsonProperty(value = "hasConnection")
        boolean hasConnection;

        public StationsInfo(String name, String line, String date, Double depth, boolean hasConnection) {
            this.name = name;
            this.line = line;
            this.date = date;
            this.depth = depth;
            this.hasConnection = hasConnection;
        }

        @Override
        public String toString() {
            return "StationsInfo{" +
                    "line='" + name + '\'' +
                    ", line='" + line + '\'' +
                    ", date='" + date + '\'' +
                    ", depth='" + depth + '\'' +
                    ", hasConnection=" + hasConnection +
                    '}';
        }
    }

    public void writeStationsInJSON(String path) throws Exception {
        File jsonStations = new File(path);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<StationsInfo> stationsInfoList = new ArrayList<>();
        for (Station station : stations) {
            StationsInfo stationsInfo = new StationsInfo(
                    station.name(),
                    getLineName(station),
                    getStationDate(station),
                    getStationDepth(station),
                    hasConnection(station)
            );
            stationsInfoList.add(stationsInfo);
        }
        objectMapper.writeValue(jsonStations, stationsInfoList);
    }

    @JsonPropertyOrder({"stations", "lines"})
    private class MetroMapInfo {

        @JsonProperty(value = "stations")
        private Map<String, String[]> stationsWithNumbers;
        @JsonProperty(value = "lines")
        private List<Line> lines;

        public MetroMapInfo(Map<String, String[]> stationsWithNumbers, List<Line> lines) {
            this.stationsWithNumbers = stationsWithNumbers;
            this.lines = lines;
        }

        @Override
        public String toString() {
            return "MetroMapInfo{" +
                    "stationsWithNumbers=" + stationsWithNumbers +
                    ", lines=" + lines +
                    '}';
        }
    }

    public void writeMetroMapInJSON(String path) throws Exception {
        File jsonMap = new File(path);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);

        List<MetroMapInfo> metroMapInfoList = new ArrayList<>();
        Map<String, String[]> stationsWithNumbers = new LinkedHashMap<>();

        for (Station station : stations) {
            stationsWithNumbers.put(
                    station.number(),
                    stations.stream()
                            .filter(s -> s.number().equals(station.number()))
                            .map(o -> o.name())
                            .toList().toArray(new String[0])
            );
        }
        metroMapInfoList.add(new MetroMapInfo(stationsWithNumbers, lines));
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonMap, metroMapInfoList);
    }

    private static String getLineName(Station station) {
        String lineName = "";
        for (Line oneLine : lines) {
            if (oneLine.number().equals(station.number())) {
                lineName = oneLine.line();
            }
        }
        return lineName;
    }

    private static String getStationDate(Station station) {
        String stationDate = "";
        for (Date date : dates) {
            if (date.getStationName().equals(station.name())) {
                stationDate = date.getDate();
            }
        }
        return stationDate;
    }

    private static Double getStationDepth(Station station) {
        Double stationDepth = null;
        for (Depth depth : depths) {
            if (!depth.getDepth().isEmpty() && depth.getDepth().equals("?")) {
                depth.setDepth("");
            } else if (depth.getStationName().equals(station.name()) && !depth.getDepth().isEmpty()) {
                stationDepth = Double.parseDouble(depth.getDepth().replace(",", "."));
            }
        }
        return stationDepth;
    }

    private static Boolean hasConnection(Station station) {
        Boolean hasConnection = false;
        for (Connection connection : connections) {
            if (connection.name().contains(station.name())) {
                return true;
            }
        }
        return hasConnection;
    }


}
