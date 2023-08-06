
public class Main {
    public static void main(String[] args) throws Exception {

        DataCollector dataCollector = new DataCollector();
        dataCollector.writeStationsInJSON("jsonTest/stations.json");
        dataCollector.writeMetroMapInJSON("jsonTest/map.json");



    }
}
