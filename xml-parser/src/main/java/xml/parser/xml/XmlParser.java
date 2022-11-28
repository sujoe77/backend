package xml.parser.xml;

import xml.parser.model.Calculation;
import xml.parser.model.CalculationType;
import xml.parser.model.Expression;
import xml.parser.model.Number;
import xml.parser.model.xml.EventType;
import xml.parser.model.xml.XMLCalculation;
import xml.parser.model.xml.XmlEvent;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.*;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static xml.parser.model.xml.EventType.END_ELEMENT;
import static xml.parser.model.xml.EventType.START_ELEMENT;

public class XmlParser {
    public static final String EXPRESSIONS = "expressions";
    private static Set<String> TAG_NAMES_FOR_NUMBER = Set.of(
            "item", "minuend", "subtrahend", "factor", "dividend", "divisor", "base", "exponent"
    );
    static Set<String> TAG_NAMES_FOR_CALCULATION = Set.of(
            "addition", "subtraction", "multiplication", "division", "power"
    );

    public static XMLCalculation getNextCalculation(XMLStreamReader streamReader) throws XMLStreamException {
        List<XmlEvent> ret = new ArrayList<>();
        String tagName = "";
        int nestLevel = 0;
        while (streamReader.hasNext() && !isComplete(ret, nestLevel)) {
            if (isEmpty(tagName) && isStartTag(streamReader) && isCalculationTag(streamReader)) {
                tagName = streamReader.getLocalName();
            }
            if (isXmlEvent(streamReader)) {
                ret.add(getXmlEvent(streamReader));
            }
            nestLevel = getNestLevel(streamReader, tagName, nestLevel);
            streamReader.next();
        }
        return new XMLCalculation(ret);
    }

    public static List<XmlEvent> extractList(XMLStreamReader streamReader) throws XMLStreamException {
        List<XmlEvent> ret = new ArrayList<>();
        while (streamReader.hasNext()) {
            streamReader.next();
            int eventType = streamReader.getEventType();
            if (isEmptyText(streamReader)) {
                continue;
            }
            if ((eventType == XMLStreamReader.START_ELEMENT || eventType == XMLStreamReader.END_ELEMENT) && !XmlParser.EXPRESSIONS.equals(streamReader.getLocalName())) {
                ret.add(getXmlEvent(streamReader));
            } else if (XmlParser.isText(streamReader)) {
                ret.add(getXmlEvent(streamReader));
            }
        }
        return ret;
    }

    public static Expression getNextExpression(XMLCalculation xmlCalculation) {
        List<XmlEvent> xmlEvents = xmlCalculation.getXmlEventList();
        return getNextExpression(xmlEvents, xmlEvents.get(0).getText(), 0, xmlEvents.size() - 1);
    }

    public static Expression getNextExpression(List<XmlEvent> xmlEvents, String name, int startIndex, int endIndex) {
        validate(xmlEvents, startIndex, endIndex);
        XmlEvent startEvent = xmlEvents.get(startIndex);
        XmlEvent endEvent = xmlEvents.get(endIndex);

        //one line text for number
        boolean isSingleNumber = startIndex == endIndex && startEvent.getType() == EventType.CHARACTERS;
        if (isSingleNumber) {
            return new Number(Double.valueOf(startEvent.getText()));
        }

        String tagName = name;
        if (isEmpty(tagName) && startEvent.getType() == START_ELEMENT) {
            tagName = startEvent.getText();
        }

        //complete number tags
        boolean isWellFormatted = isWellFormatted(startEvent, endEvent, tagName);
        if (!isWellFormatted) {
            int newStartIndex = findNextIndex(xmlEvents, tagName, startIndex, endIndex, START_ELEMENT);
            int newEndIndex = findNextIndex(xmlEvents, tagName, startIndex, endIndex, END_ELEMENT);
            return getNextExpression(xmlEvents, tagName, newStartIndex, newEndIndex);
        }

        if (isNumberTag(startEvent)) {
            return getNextExpression(xmlEvents, "", startIndex + 1, endIndex - 1);
        }

        if (isCalculationTag(startEvent)) {
            return createCalculation(xmlEvents, startIndex, endIndex, startEvent, tagName);
        }
        return null;
    }

    private static Calculation createCalculation(List<XmlEvent> xmlEvents, int startIndex, int endIndex, XmlEvent startEvent, String tagName) {
        CalculationType type = CalculationType.fromName(tagName);
        if (type == CalculationType.SUBTRACTION) {
            return createSubtraction(xmlEvents, startIndex, endIndex, startEvent, type);
        } else if (type == CalculationType.DIVISION) {
            return createDivision(xmlEvents, startIndex, endIndex, startEvent, type);
        } else if (type == CalculationType.ADDITION || type == CalculationType.MULTIPLICATION) {
            return createCalculation(xmlEvents, startIndex, endIndex, startEvent, type);
        } else if (type == CalculationType.POWER) {
            return createPower(xmlEvents, startIndex, endIndex, startEvent, type);
        }
        return null;
    }

    private static Calculation createCalculation(List<XmlEvent> xmlEvents, int startIndex, int endIndex, XmlEvent startEvent, CalculationType type) {
        Calculation ret = new Calculation(startEvent.getId(), type);
        int currentPos = startIndex + 1;
        while (true) {
            String subTag = type == CalculationType.ADDITION ? "item" : "factor";
            int newStartIndex = findNextIndex(xmlEvents, subTag, currentPos, endIndex, START_ELEMENT);
            int newEndIndex = findNextIndex(xmlEvents, subTag, currentPos, endIndex, END_ELEMENT);
            if (newStartIndex != -1) {
                ret.getSubExpressions().add(getNextExpression(xmlEvents, subTag, newStartIndex, newEndIndex));
            } else {
                break;
            }
            currentPos = newEndIndex + 1;
        }
        return ret;
    }

    private static Calculation createDivision(List<XmlEvent> xmlEvents, int startIndex, int endIndex, XmlEvent startEvent, CalculationType type) {
        Calculation ret = new Calculation(startEvent.getId(), type);
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "dividend", startIndex + 1, endIndex));
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "divisor", startIndex + 1, endIndex));
        return ret;
    }

    private static Calculation createSubtraction(List<XmlEvent> xmlEvents, int startIndex, int endIndex, XmlEvent startEvent, CalculationType type) {
        Calculation ret = new Calculation(startEvent.getId(), type);
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "minuend", startIndex + 1, endIndex));
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "subtrahend", startIndex + 1, endIndex));
        return ret;
    }

    private static Calculation createPower(List<XmlEvent> xmlEvents, int startIndex, int endIndex, XmlEvent startEvent, CalculationType type) {
        Calculation ret = new Calculation(startEvent.getId(), type);
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "base", startIndex + 1, endIndex));
        ret.getSubExpressions().add(getNextExpression(xmlEvents, "exponent", startIndex + 1, endIndex));
        return ret;
    }

    private static boolean isNumberTag(XmlEvent startEvent) {
        return TAG_NAMES_FOR_NUMBER.contains(startEvent.getText());
    }

    private static boolean isWellFormatted(XmlEvent startEvent, XmlEvent endEvent, String tagName) {
        return startEvent.getType() == START_ELEMENT && endEvent.getType() == EventType.END_ELEMENT
                && startEvent.getText().equals(tagName)
                && startEvent.getText().equals(endEvent.getText());
    }

    private static void validate(List<XmlEvent> xmlEvents, int startIndex, int endIndex) {
    }

    private static int findNextIndex(List<XmlEvent> xmlEvents, String name, int startIndex, int endIndex, EventType type) {
        int nestedLevel = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            XmlEvent xmlEvent = xmlEvents.get(i);
            if (xmlEvent.getType() == START_ELEMENT && name.equals(xmlEvent.getText())) {
                nestedLevel++;
                if (type == START_ELEMENT && nestedLevel == 1) {
                    return i;
                }
            }
            if (xmlEvent.getType() == END_ELEMENT && name.equals(xmlEvent.getText())) {
                nestedLevel--;
                if (type == END_ELEMENT && (nestedLevel == 0 || nestedLevel == -1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isCalculationTag(XMLStreamReader streamReader) {
        return isCalculationTag(streamReader.getLocalName());
    }

    public static boolean isCalculationTag(XmlEvent event) {
        return isCalculationTag(event.getText());
    }

    public static boolean isCalculationTag(String text) {
        return TAG_NAMES_FOR_CALCULATION.contains(text);
    }

    public static boolean isXmlEvent(XMLStreamReader streamReader) {
        return isStartTag(streamReader) || isEndTag(streamReader) || isText(streamReader);
    }

    static boolean isEndTag(XMLStreamReader streamReader) {
        return streamReader.getEventType() == XMLStreamReader.END_ELEMENT && !EXPRESSIONS.equals(streamReader.getLocalName());
    }

    static boolean isStartTag(XMLStreamReader streamReader) {
        return streamReader.getEventType() == XMLStreamReader.START_ELEMENT && !EXPRESSIONS.equals(streamReader.getLocalName());
    }

    static boolean isText(XMLStreamReader streamReader) {
        return streamReader.getEventType() == CHARACTERS && streamReader.hasText() && !streamReader.getText().trim().isEmpty();
    }

    public static boolean isComplete(List<XmlEvent> ret, int nestLevel) {
        return !ret.isEmpty() && nestLevel == 0;
    }

    public static int getNestLevel(XMLStreamReader streamReader, String tagName, int nestLevel) {
        boolean startTag = isStartTag(streamReader);
        boolean endTag = isEndTag(streamReader);
        if (startTag || endTag) {
            if (streamReader.getLocalName().equals(tagName)) {
                return startTag ? nestLevel + 1
                        : nestLevel - 1;
            }
        }
        return nestLevel;
    }

    private static XmlEvent getXmlEvent(XMLStreamReader streamReader) {
        return new XmlEvent(getId(streamReader), EventType.fromInt(streamReader.getEventType()), getText(streamReader));
    }

    private static String getText(XMLStreamReader streamReader) {
        return streamReader.getEventType() == CHARACTERS ? streamReader.getText() : streamReader.getLocalName();
    }

    private static boolean isEmptyText(XMLStreamReader streamReader) {
        return streamReader.getEventType() == CHARACTERS && streamReader.getText().trim().isEmpty();
    }

    private static int getId(XMLStreamReader streamReader) {
        Map<String, String> attributes = getAttributes(streamReader);
        String ret = attributes.get("id");
        return isEmpty(ret) ? 0 : Integer.parseInt(ret);
    }

    private static Map<String, String> getAttributes(XMLStreamReader streamReader) {
        Map<String, String> ret = new HashMap<>();
        if (streamReader.getEventType() != XMLStreamReader.START_ELEMENT) {
            return ret;
        }
        for (int i = 0; i < streamReader.getAttributeCount(); i++) {
            ret.put(streamReader.getAttributeLocalName(i), streamReader.getAttributeValue(i));
        }
        return ret;
    }
}
