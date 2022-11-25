# xml-parser

This module implemented a small program can deserialize some calculation from xml file and output the calculation result to another xml file.

# Design Considerations

## Xml libs

  Most popular XML libs include
  
  - DOM
  - SAX
  - StAX
  - JAXB

  ![](https://www.baeldung.com/wp-content/uploads/2016/07/xmlapi_table-1.png)

When we select Java libs for XML processing, we need to consider below:

  - Widely used
  - Support Streaming
  - Support complex model
  
  Since the file can be very big, we need to read and process the file in streaming mode,
  also because we have some nested structure, we need to deal with complex model.

  for reasons above, we choose StAX (https://docs.oracle.com/cd/E19575-01/819-3669/6n5sg7bni/index.html)
  which is included in Jdk 11, and implement a Xml parser on it.

## Data model

  we need to have a data model which

  - support nested structure
  - easy to extend
    
  So we introduced an interface xml.parser.model.Expression, which represent an entity can be calculated and return a result.
  The interface have 2 implementations

| Class | XML                        | Java                               |
|-------| --------------------------------- | ----------------------------------------- |
| Number| ```<item>2</item>```       | ```new Number(2);``` |
| Calculation | ```<subtraction id="2"><minuend>3</minuend><subtrahend>2</subtrahend></subtraction>``` | ```Expression e = new Calculation(2, SUBTRACTION); e.addExpressions(List.of(new Number(3), new Number(2));``` |

* Calculation class

```
public class Calculation implements Expression {
    private final int id; //operation id
    private final CalculationType type; //operation type, addition, subtraction ...
    private final List<Expression> subExpressions = new ArrayList<>(); //item, minuend, factor etc
```

Each ```<addition>, <subtraction>, <multiplication>, <division>``` can be modeled as a Calculation.

and ```<item>, <minuend>, <subtrahend>, <factor> ... ``` are in list subExpressions.

for example subtraction has 2 subExpressions, 1 is minuend, another is subtrahend, each can be a Number or another Calculation.

## Extensibility



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

## XML parser

we implement our own XML parser ([xml.parser.xml.XmlParser](src/java/main/xml/parser/xml/XmlParser.java)) based on StAX.

# Unit tests

we have 2 unit test classes

| Class(es)                         | description                               |
| --------------------------------- | ----------------------------------------- |
| xml.parser.model.ReaderTest | Test Xml can be read and deserialized correctly |
| xml.parser.model.CalculationTest | Test calculations |



# File list

| folder                            | description                               |
| --------------------------------- | ----------------------------------------- |
| src/main/java                     | java code                                 |
| src/test/resources/input          | input test xml files                      |
| src/main/resources/output         | output xml files                          |