import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";

        long start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + " ms");

        DBConnection.printVoterCounts();
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        findEqualVoters(handler);
        //fixWorkTimes(doc);
    }

    private static void findEqualVoters(XMLHandler handler) throws Exception {
        List<Voter> list = handler.getVotersList();
        list.forEach(voter -> DBConnection.countVoter(voter.getName(), birthDayFormat.format(voter.getBirthDay())));
        DBConnection.executeMultiInsert();
    }
}