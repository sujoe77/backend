package xml.parser;

import org.apache.commons.io.FileUtils;
import xml.parser.io.JsonReader;
import xml.parser.io.JsonWriter;
import xml.parser.io.xml.XmlStreamReader;
import xml.parser.io.xml.XmlStreamWriter;
import xml.parser.model.Calculation;
import xml.parser.model.Expression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String inputFolder = args[0];
        String outputFolder = args[1];
        Collection<File> fileList = FileUtils.listFiles(new File(inputFolder), new String[]{"xml"}, false);
        fileList.forEach(inputFile -> processXml(inputFile, getOutputFile(outputFolder, inputFile.getName(), "xml")));
    }

    private static void processXml(File inputFile, File outputFile) {
        try (InputStream inputStream = new FileInputStream(inputFile); FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            Stream<Expression> expressionStream = new XmlStreamReader().deserialize(inputStream);
            new XmlStreamWriter().serialize(expressionStream, outputStream);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static File getOutputFile(String outputFolder, String name, String extension) {
        return new File(outputFolder + "/" + name.replace("." + extension, "_result." + extension));
    }
}
