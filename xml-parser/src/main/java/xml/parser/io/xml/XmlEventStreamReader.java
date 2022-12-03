package xml.parser.io.xml;

import xml.parser.io.Deserializer;
import xml.parser.model.xml.XmlEvent;
import xml.parser.xml.XmlParser;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static xml.parser.xml.XmlParser.getXmlEvent;
import static xml.parser.xml.XmlParser.isEmptyText;

public class XmlEventStreamReader implements Deserializer<InputStream, Stream<XmlEvent>> {

    @Override
    public Stream<XmlEvent> deserialize(InputStream input) {
        try {
            XMLStreamReader streamReader = XmlStreamReader.initReader(input);
            Iterator<XmlEvent> iterator = iterator(streamReader);
            Stream<XmlEvent> stream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false
            );
            return stream.filter(Objects::nonNull);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Iterator<XmlEvent> iterator(XMLStreamReader streamReader) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    return streamReader.hasNext();
                } catch (XMLStreamException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public XmlEvent next() {
                XmlEvent ret = null;
                try {
                    while (streamReader.hasNext()) {
                        streamReader.next();
                        int eventType = streamReader.getEventType();
                        if (isEmptyText(streamReader)) {
                            continue;
                        }
                        if ((eventType == XMLStreamReader.START_ELEMENT || eventType == XMLStreamReader.END_ELEMENT) && !XmlParser.EXPRESSIONS.equals(streamReader.getLocalName())) {
                            return getXmlEvent(streamReader);
                        } else if (XmlParser.isText(streamReader)) {
                            return getXmlEvent(streamReader);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return ret;
            }
        };
    }
}
