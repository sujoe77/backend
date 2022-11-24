package xml.parser.io.xml;

import xml.parser.io.Deserializer;
import xml.parser.xml.XmlParser;
import xml.parser.model.Expression;
import xml.parser.model.xml.XMLCalculation;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static xml.parser.xml.XmlParser.getNextCalculation;

public class XmlStreamReader implements Deserializer<InputStream, Stream<Expression>> {
    @Override
    public Stream<Expression> deserialize(InputStream input) {
        try {
            XMLStreamReader streamReader = initReader(input);
            Iterator<XMLCalculation> iterator = XmlStreamReader.iterator(streamReader);
            Stream<XMLCalculation> stream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false
            );
            return stream.map(XmlParser::getNextExpression);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static XMLStreamReader initReader(String path) throws XMLStreamException, FileNotFoundException {
        return initReader(new FileInputStream(path));
    }

    public static XMLStreamReader initReader(InputStream inputStream) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(inputStream);
    }

    public static Iterator<XMLCalculation> iterator(XMLStreamReader streamReader) {
        return new Iterator<>() {
            private XMLCalculation next;
            private XMLCalculation afterNext;

            @Override
            public boolean hasNext() {
                if (next == null) {
                    next();
                }
                return !next.isEmpty();
            }

            @Override
            public XMLCalculation next() {
                try {
                    XMLCalculation ret;
                    if (next == null) {
                        next = getNextCalculation(streamReader);
                        ret = next;
                    } else {
                        ret = next;
                        next = afterNext;
                    }
                    afterNext = getNextCalculation(streamReader);
                    if (ret.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                    return ret;
                } catch (XMLStreamException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
