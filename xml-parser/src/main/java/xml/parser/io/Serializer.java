package xml.parser.io;

public interface Serializer<I, O> {
    void serialize(I input, O output);
}
