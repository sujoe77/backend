package xml.parser.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import xml.parser.io.json.ExpressionAdapter;
import xml.parser.model.Calculation;
import xml.parser.model.Expression;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader implements Deserializer<InputStream, Stream<Calculation>> {
    @Override
    public Stream<Calculation> deserialize(InputStream input) {
        try {
            String content = IOUtils.toString(input, StandardCharsets.UTF_8);
            Type listType = new TypeToken<ArrayList<Calculation>>() {
            }.getType();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Expression.class, new ExpressionAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            List<Calculation> ret = gson.fromJson(content, listType);
            return ret.stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
