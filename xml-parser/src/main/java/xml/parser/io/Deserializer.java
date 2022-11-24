package xml.parser.io;

public interface Deserializer<I, O> {
    O deserialize(I input);
}
