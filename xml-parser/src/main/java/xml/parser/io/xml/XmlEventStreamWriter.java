package xml.parser.io.xml;

import xml.parser.io.Serializer;
import xml.parser.model.Calculation;
import xml.parser.model.Result;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

import static xml.parser.io.xml.XmlStreamWriter.initWriter;

public class XmlEventStreamWriter implements Serializer<Stream<Result>, OutputStream> {
    @Override
    public void serialize(Stream<Result> input, OutputStream output) {
        try {
            XMLStreamWriter writer = initWriter(output);
            XmlStreamWriter.writeXmlStart(writer);
            input.forEach(e -> XmlStreamWriter.writeXml(writer, e.getId(), e.getResult()));
            XmlStreamWriter.writeXmlEnd(writer);
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
