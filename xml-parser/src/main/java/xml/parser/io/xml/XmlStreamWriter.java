package xml.parser.io.xml;

import xml.parser.io.Serializer;
import xml.parser.model.Calculation;
import xml.parser.model.Expression;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

public class XmlStreamWriter implements Serializer<Stream<Expression>, OutputStream> {

    @Override
    public void serialize(Stream<Expression> expressionStream, OutputStream output) {
        try {
            XMLStreamWriter writer = initWriter(output);
            XmlStreamWriter.writeXmlStart(writer);
            expressionStream
                    .forEach(e -> XmlStreamWriter.writeXml(writer, ((Calculation) e).getId(), e.calculate()));
            XmlStreamWriter.writeXmlEnd(writer);
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLStreamWriter initWriter(String path) throws XMLStreamException, IOException {
        return initWriter(new File(path));
    }

    public static XMLStreamWriter initWriter(File file) throws XMLStreamException, IOException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        return factory.createXMLStreamWriter(new FileWriter(file));
    }

    public static XMLStreamWriter initWriter(OutputStream outputStream) throws XMLStreamException, IOException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        return factory.createXMLStreamWriter(outputStream);
    }

    public static void writeXmlStart(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("expressions");
    }

    public static void writeXml(XMLStreamWriter writer, int id, double result) {
        try {
            writer.writeStartElement("result");
            writer.writeAttribute("id", String.valueOf(id));
            writer.writeCharacters(String.valueOf(result));
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeXmlEnd(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}
