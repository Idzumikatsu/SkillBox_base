import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";

        long start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        DBConnection.writeDataIntoDB(handler);
    }
}