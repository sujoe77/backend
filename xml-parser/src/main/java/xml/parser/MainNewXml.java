package xml.parser;

import org.apache.commons.io.FileUtils;
import xml.parser.io.xml.XmlEventStreamReader;
import xml.parser.io.xml.XmlEventStreamWriter;
import xml.parser.model.Calculation;
import xml.parser.model.CalculationType;
import xml.parser.model.Number;
import xml.parser.model.Result;
import xml.parser.model.xml.EventType;
import xml.parser.model.xml.XmlEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Stream;

import static xml.parser.model.xml.EventType.CHARACTERS;
import static xml.parser.xml.XmlParser.TAG_NAMES_FOR_CALCULATION;

public class MainNewXml {
    public static void main(String[] args) {
        String inputFolder = args[0];
        String outputFolder = args[1];
        Collection<File> fileList = FileUtils.listFiles(new File(inputFolder), new String[]{"xml"}, false);
        fileList.forEach(inputFile -> processXml(inputFile, getOutputFile(outputFolder, inputFile.getName(), "xml")));
    }

    private static void processXml(File inputFile, File outputFile) {
        try (InputStream inputStream = new FileInputStream(inputFile); FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            Stream<XmlEvent> eventStream = new XmlEventStreamReader().deserialize(inputStream);
            Stream<Result> resultStream = parseAndCalculate(eventStream);
            new XmlEventStreamWriter().serialize(resultStream, outputStream);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<Result> parseAndCalculate(Stream<XmlEvent> xmlEventStream) {
        Stack<Calculation> stack = new Stack<>();
        return xmlEventStream
                .filter(Objects::nonNull)
                .map(e -> {
                    if (e.getType() == EventType.START_ELEMENT && TAG_NAMES_FOR_CALCULATION.contains(e.getText())) {
                        Calculation cal = new Calculation(e.getId(), CalculationType.fromName(e.getText()));
                        stack.push(cal);
                    } else if (e.getType() == CHARACTERS) {
                        stack.peek().getSubExpressions().add(new Number(Double.parseDouble(e.getText())));
                    } else if (e.getType() == EventType.END_ELEMENT && TAG_NAMES_FOR_CALCULATION.contains(e.getText())) {
                        Calculation nextCal = stack.peek();
                        double result = nextCal.calculate();
                        if (stack.size() == 1) {
                            stack.pop();
                            return new Result(nextCal.getId(), result);
                        } else {
                            stack.pop();
                            stack.peek().getSubExpressions().add(new Number(result));
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull);
    }

    private static File getOutputFile(String outputFolder, String name, String extension) {
        return new File(outputFolder + "/" + name.replace("." + extension, "_new_result." + extension));
    }
}
