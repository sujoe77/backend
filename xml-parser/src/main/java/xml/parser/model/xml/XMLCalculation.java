package xml.parser.model.xml;

import java.util.List;

public class XMLCalculation {
    private final List<XmlEvent> xmlEventList;

    public XMLCalculation(List<XmlEvent> xmlEventList) {
        this.xmlEventList = xmlEventList;
    }

    public boolean isEmpty() {
        return xmlEventList == null || xmlEventList.isEmpty();
    }

    public List<XmlEvent> getXmlEventList() {
        return xmlEventList;
    }
}
