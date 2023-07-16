import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTests extends TestCase {
    List<Station> route;
    List<Station> stations;
    List<Line> lines;
    StationIndex stationIndex = new StationIndex();

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        lines = List.of(line1, line2, line3);

        var aku = new Station("Акулья", line1);
        var pyt = new Station("Питонья", line1);
        var pyt1 = new Station("Питонья1", line1);
        var pyt2 = new Station("Питонья2", line1);
        var jav = new Station("Жабья", line2);
        var php = new Station("Пхпешная", line2);
        var sha = new Station("Шарпейская", line3);
        var plus = new Station("Крестовая", line3);
        stations = List.of(aku, pyt, pyt1, pyt2, jav, php, sha, plus);

        route.addAll(stations);

        List<Station> line1list = route.subList(0, 4);
        List<Station> line2list = route.subList(4, 6);
        List<Station> line3list = route.subList(6, 8);
        line1list.forEach(line1::addStation);
        line2list.forEach(line2::addStation);
        line3list.forEach(line3::addStation);

        lines.forEach(line -> stationIndex.addLine(line));
        stations.forEach(station -> stationIndex.addStation(station));

        stationIndex.addConnection(List.of(pyt2, jav));
        stationIndex.addConnection(List.of(php, sha));

    }

    public void testCalculateDuration() {
        double expected = 19.5;
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteOnTheLine() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stationIndex.getStation("Акулья",1),
                stationIndex.getStation("Питонья1",1));
        List<Station> expected = route.subList(0, 3);
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithOneConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stationIndex.getStation("Питонья1",1),
                stationIndex.getStation("Пхпешная",2));
        List<Station> expected = route.subList(2, 6);
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteWithTwoConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(
                stationIndex.getStation("Акулья",1),
                stationIndex.getStation("Шарпейская", 3));
        List<Station> expected = route.subList(0, 7);
        assertEquals(expected, actual);
    }
}
