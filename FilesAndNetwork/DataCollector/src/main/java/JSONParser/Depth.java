package JSONParser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Depth {

    @JsonProperty(value = "station_name")
    private String stationName;

    @JsonProperty(value = "depth")
    private String depth;

    public Depth() {
    }

    public Depth(String stationName, String depth) {
        this.stationName = stationName;
        this.depth = depth;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depth depth1 = (Depth) o;
        return Objects.equals(stationName, depth1.stationName) && Objects.equals(depth, depth1.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, depth);
    }

    @Override
    public String toString() {
        return "Depth{" +
                "stationName=" + stationName +
                ", depth=" + depth +
                '}';
    }
}
