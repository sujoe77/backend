# xml-parser

This module implemented a small program can deserialize some calculation from xml file and output the calculation result to another xml file.

# Design Considerations

* Big files  
* Complex operations
* Extensibility

# Implementation

## xml.parser.Main class

This class can read from input folder (args[0]) and write to output folder (args[1])

## Data model

package xml.parser.model defined data models we used.

includes classes below:

| Class(es)                         | description                               |
| --------------------------------- | ----------------------------------------- |
| xml.parser.model.Expression       | An interface defined a number or expression can be calculated and return a number, with can be used in xml tag <item>, <minuend>, <factor> etc |
| xml.parser.model.Number           | represent a number, implement Expression interface, which calculate return the number |
| xml.parser.model.Calculation      | represent a operation, i.e. <addition>, <substraction>, <multiplication>, <division>, which include an id, a calculation type and list of sub-expressions (can be a number or expression), when we call calculate, the calculation will be executed recursively |

package xml.parser.model.xml includes classes used in xml parsing.  

## xml.parser.io.Serializer and xml.parser.io.Deserializer

These 2 generic typed interfaces defined 2 main operations of serialization and deserialization.

in our implementation, they have 2 implementations.

| Class(es)                         | description                               |
| --------------------------------- | ----------------------------------------- |
| xml.parser.io.xml.XmlStreamReader | A Deserializer which can accept an InputStream and output a Stream<Expression> |
| xml.parser.io.xml.XmlStreamWriter | A Serializer which can accept an Stream<Expression> and write to an OutputStream |



# File list

| folder                            | description                               |
| --------------------------------- | ----------------------------------------- |
| src/main/java                     | java code                                 |
| src/test/resources/input          | input test xml files                      |
| src/main/resources/output         | output xml files                          |
