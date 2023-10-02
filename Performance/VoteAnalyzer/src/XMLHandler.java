import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private List<Voter> votersList;

    public XMLHandler() {
        votersList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDayFormat.format(birthDay));
                votersList.add(voter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    public List<Voter> getVotersList() {
        return votersList;
    }
}
