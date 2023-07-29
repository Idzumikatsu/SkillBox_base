package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonFile = Files.readString(Paths.get("src/main/resources/map.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(jsonFile);
        JsonNode stations = jsonData.get("stations");
        JsonNode lines = jsonData.get("lines");

        for (JsonNode line : lines) {
            ObjectNode lineNode = (ObjectNode) line;
            lineNode.remove("color");
            lineNode.put("stationsCount", calcStationsCountOnLine(lineNode,stations));
        }

        ObjectMapper mapper = new ObjectMapper();
        File output = new File("src/main/resources/editedMap.json");
        mapper.writeValue(output, lines);
    }

    public static int calcStationsCountOnLine (ObjectNode line, JsonNode stations) {
        return stations.get(String.valueOf(line.get("number"))).size();
    }
}