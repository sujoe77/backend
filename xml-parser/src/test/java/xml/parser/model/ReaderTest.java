package xml.parser.model;

import org.junit.jupiter.api.Test;
import xml.parser.xml.XmlParser;
import xml.parser.io.xml.XmlStreamReader;
import xml.parser.io.xml.XmlStreamWriter;
import xml.parser.model.xml.EventType;
import xml.parser.model.xml.XMLCalculation;
import xml.parser.model.xml.XmlEvent;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    @Test
    public void testRead() throws FileNotFoundException, XMLStreamException {
        XMLStreamReader streamReader = XmlStreamReader.initReader("src/test/resources/input/data01.xml");
        List<XmlEvent> ret = XmlParser.extractList(streamReader);
        assertEquals(38, ret.size());
        assertEquals(new XmlEvent(1, EventType.START_ELEMENT, "addition"), ret.get(0));
        assertEquals(new XmlEvent(0, EventType.END_ELEMENT, "division"), ret.get(37));
    }

    @Test
    public void testRead2() throws FileNotFoundException, XMLStreamException {
        XMLStreamReader streamReader = XmlStreamReader.initReader("src/test/resources/input/data02.xml");
        List<XmlEvent> ret = XmlParser.extractList(streamReader);
        assertEquals(89, ret.size());
        assertEquals(new XmlEvent(10, EventType.START_ELEMENT, "addition"), ret.get(0));
        assertEquals(new XmlEvent(0, EventType.END_ELEMENT, "division"), ret.get(88));
        System.out.println(ret);
    }

    @Test
    public void testReadWrite() throws XMLStreamException, IOException {
        XMLStreamReader streamReader = XmlStreamReader.initReader("src/test/resources/input/data01.xml");
        XMLStreamWriter streamWriter = XmlStreamWriter.initWriter("src/test/resources/output/data01_result.xml");
        XmlStreamWriter.writeXmlStart(streamWriter);
        Iterator<XMLCalculation> iterator = XmlStreamReader.iterator(streamReader);
        Stream<XMLCalculation> stream = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false
        );
        stream.map(XmlParser::getNextExpression)
                .forEach(e -> XmlStreamWriter.writeXml(streamWriter, ((Calculation) e).getId(), e.calculate()));
        XmlStreamWriter.writeXmlEnd(streamWriter);
    }

    @Test
    public void testGetNextCalculation() throws IOException, XMLStreamException {
        XMLStreamReader streamReader = XmlStreamReader.initReader("src/test/resources/input/data01.xml");
        XMLStreamWriter streamWriter = XmlStreamWriter.initWriter("src/test/resources/output/data01_result.xml");
        XmlStreamWriter.writeXmlStart(streamWriter);

        Iterator<XMLCalculation> iterator = XmlStreamReader.iterator(streamReader);
        assertTrue(iterator.hasNext());
        XMLCalculation xmlCalculation = iterator.next();
        List<XmlEvent> eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(11, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(1, EventType.START_ELEMENT, "addition"));
        assertEquals(eventList.get(10), new XmlEvent(0, EventType.END_ELEMENT, "addition"));

        Expression expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(9, expression.calculate());
        XmlStreamWriter.writeXml(streamWriter, ((Calculation) expression).getId(), expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(8, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(2, EventType.START_ELEMENT, "subtraction"));
        assertEquals(eventList.get(7), new XmlEvent(0, EventType.END_ELEMENT, "subtraction"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(1, expression.calculate());
        XmlStreamWriter.writeXml(streamWriter, ((Calculation) expression).getId(), expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(11, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(3, EventType.START_ELEMENT, "multiplication"));
        assertEquals(eventList.get(10), new XmlEvent(0, EventType.END_ELEMENT, "multiplication"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(240, expression.calculate());
        XmlStreamWriter.writeXml(streamWriter, ((Calculation) expression).getId(), expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(8, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(4, EventType.START_ELEMENT, "division"));
        assertEquals(eventList.get(7), new XmlEvent(0, EventType.END_ELEMENT, "division"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(6, expression.calculate());
        XmlStreamWriter.writeXml(streamWriter, ((Calculation) expression).getId(), expression.calculate());

        assertFalse(iterator.hasNext());
        boolean withException = false;
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            withException = true;
        }
        assertTrue(withException);
        XmlStreamWriter.writeXmlEnd(streamWriter);
    }

    @Test
    public void testGetNextCalculation2() throws FileNotFoundException, XMLStreamException {
        XMLStreamReader streamReader = XmlStreamReader.initReader("src/test/resources/input/data02.xml");
        Iterator<XMLCalculation> iterator = XmlStreamReader.iterator(streamReader);
        assertTrue(iterator.hasNext());
        XMLCalculation xmlCalculation = iterator.next();
        List<XmlEvent> eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(18, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(10, EventType.START_ELEMENT, "addition"));
        assertEquals(eventList.get(17), new XmlEvent(0, EventType.END_ELEMENT, "addition"));
        assertEquals(eventList.get(8), new XmlEvent(0, EventType.START_ELEMENT, "subtraction"));
        assertEquals(eventList.get(15), new XmlEvent(0, EventType.END_ELEMENT, "subtraction"));

        Expression expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(9, expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(8, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(11, EventType.START_ELEMENT, "subtraction"));
        assertEquals(eventList.get(7), new XmlEvent(0, EventType.END_ELEMENT, "subtraction"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(1, expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(11, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(12, EventType.START_ELEMENT, "multiplication"));
        assertEquals(eventList.get(10), new XmlEvent(0, EventType.END_ELEMENT, "multiplication"));

        assertEquals(eventList.get(2), new XmlEvent(0, EventType.CHARACTERS, "5"));
        assertEquals(eventList.get(5), new XmlEvent(0, EventType.CHARACTERS, "6"));
        assertEquals(eventList.get(8), new XmlEvent(0, EventType.CHARACTERS, "8"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(240, expression.calculate());


        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(37, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(13, EventType.START_ELEMENT, "multiplication"));
        assertEquals(eventList.get(36), new XmlEvent(0, EventType.END_ELEMENT, "multiplication"));
        assertEquals(eventList.get(2), new XmlEvent(0, EventType.START_ELEMENT, "addition"));
        assertEquals(eventList.get(12), new XmlEvent(0, EventType.END_ELEMENT, "addition"));
        assertEquals(eventList.get(18), new XmlEvent(0, EventType.START_ELEMENT, "multiplication"));
        assertEquals(eventList.get(34), new XmlEvent(0, EventType.END_ELEMENT, "multiplication"));

        assertEquals(eventList.get(4), new XmlEvent(0, EventType.CHARACTERS, "2"));
        assertEquals(eventList.get(7), new XmlEvent(0, EventType.CHARACTERS, "3"));
        assertEquals(eventList.get(10), new XmlEvent(0, EventType.CHARACTERS, "4"));
        assertEquals(eventList.get(15), new XmlEvent(0, EventType.CHARACTERS, "6"));
        assertEquals(eventList.get(20), new XmlEvent(0, EventType.CHARACTERS, "3"));
        assertEquals(eventList.get(23), new XmlEvent(0, EventType.CHARACTERS, "4"));
        assertEquals(eventList.get(26), new XmlEvent(0, EventType.CHARACTERS, "5"));
        assertEquals(eventList.get(29), new XmlEvent(0, EventType.CHARACTERS, "10"));
        assertEquals(eventList.get(32), new XmlEvent(0, EventType.CHARACTERS, "56"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals((2 + 3 + 4) * 6 * (3 * 4 * 5 * 10 * 56), expression.calculate());

        assertTrue(iterator.hasNext());
        xmlCalculation = iterator.next();
        eventList = xmlCalculation.getXmlEventList();
        System.out.println(eventList);
        assertEquals(15, eventList.size());
        assertEquals(eventList.get(0), new XmlEvent(14, EventType.START_ELEMENT, "division"));
        assertEquals(eventList.get(14), new XmlEvent(0, EventType.END_ELEMENT, "division"));
        assertEquals(eventList.get(5), new XmlEvent(0, EventType.START_ELEMENT, "addition"));
        assertEquals(eventList.get(12), new XmlEvent(0, EventType.END_ELEMENT, "addition"));

        assertEquals(eventList.get(2), new XmlEvent(0, EventType.CHARACTERS, "54"));
        assertEquals(eventList.get(7), new XmlEvent(0, EventType.CHARACTERS, "3"));
        assertEquals(eventList.get(10), new XmlEvent(0, EventType.CHARACTERS, "6"));

        expression = XmlParser.getNextExpression(xmlCalculation);
        assertEquals(56 / (3 + 6), expression.calculate());
    }
}