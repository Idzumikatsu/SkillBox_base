import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\Евгений\\Desktop\\SkillBox\\MovementsParser\\data\\movementList.csv";
        String regex = "[^a-zA-Z0-9]([a-zA-Z0-9\s]+)[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\s[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}";
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path));
            lines.remove(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Double> result = new HashMap<>();
        String[] tableColumns;
        for (String line : lines) {
            tableColumns = line.split(","); // 8 length [5]-operation info [6]-income [7]-outcome
            Pattern pattern = Pattern.compile(regex);
            Double outcome = Double.valueOf(tableColumns[7]);
            pattern.matcher(tableColumns[5])
                    .results()
                    .map(matchResult -> matchResult.group(1).strip())
                    .forEach(name -> result.put(name, outcome));

        }
        result.forEach((key, value) -> System.out.println(key + " " + value));
    }
}