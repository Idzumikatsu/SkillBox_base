package CSVParser;

import java.util.Objects;

public class Date {
    String stationName;
    String date;

    public Date(String stationName, String date) {
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(stationName, date1.stationName) && Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, date);
    }

    @Override
    public String toString() {
        return "Date{" +
                "stationName=" + stationName +
                ", date=" + date +
                '}';
    }
}
