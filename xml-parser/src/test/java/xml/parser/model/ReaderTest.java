package xml.parser.model;

import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderTest {
    @Test
    public void testRead() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader("src/test/resources/data01.xml"));

        while (streamReader.hasNext()) {
            streamReader.next();
            if(streamReader.getEventType() == XMLStreamReader.CHARACTERS && streamReader.getText().trim().isEmpty()){
                continue;
            }
            System.out.println("Event type is: " + streamReader.getEventType());
            if (streamReader.getEventType() == XMLStreamReader.START_ELEMENT || streamReader.getEventType() == XMLStreamReader.END_ELEMENT) {
                System.out.println(streamReader.getLocalName());
            }
            if (streamReader.hasText() && !streamReader.getText().isEmpty()) {
                System.out.println("text is: " + streamReader.getText());
            }
        }
    }
}
