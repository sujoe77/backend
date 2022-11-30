package xml.parser.io.xml;

import org.junit.jupiter.api.Test;
import xml.parser.model.xml.XmlEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlEventStreamReaderTest {

    @Test
    void deserialize() throws FileNotFoundException {
        XmlEventStreamReader xmlEVentStreamReader = new XmlEventStreamReader();
        Stream<XmlEvent> stream = xmlEVentStreamReader.deserialize(new FileInputStream("src/test/resources/input/data01.xml"));
        List<XmlEvent> tags = stream.collect(Collectors.toList());
        assertEquals(39, tags.size());
    }
}