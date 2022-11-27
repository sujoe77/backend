package xml.parser.io;

import com.google.gson.Gson;
import xml.parser.model.Calculation;
import xml.parser.model.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonWriter implements Serializer<Stream<Calculation>, OutputStream> {
    @Override
    public void serialize(Stream<Calculation> input, OutputStream output) {
        Gson gson = new Gson();
        Collection<Result> list = input.map(e -> new Result(e.calculate())).collect(Collectors.toList());
        try {
            output.write(gson.toJson(list).getBytes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
