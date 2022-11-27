package xml.parser.io.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import xml.parser.model.Calculation;
import xml.parser.model.CalculationType;
import xml.parser.model.Expression;
import xml.parser.model.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionAdapter extends TypeAdapter<Expression> {
    @Override
    public void write(JsonWriter out, Expression value) throws IOException {

    }

    @Override
    public Expression read(JsonReader reader) throws IOException {
        return readExpression(reader);
    }

    private Expression readExpression(JsonReader reader) throws IOException {
        JsonToken token = reader.peek();
        String fieldName = null;
        Expression ret = null;

        if (token.equals(JsonToken.BEGIN_OBJECT)) {
            reader.beginObject();
            return readExpression(reader);
        } else if (token.equals(JsonToken.NAME)) {
            fieldName = reader.nextName();
        } else if (token.equals(JsonToken.END_OBJECT)) {
            reader.endObject();
            return ret;
        }

        if ("number".equals(fieldName)) {
            //move to next token
            ret = readNumber(reader);
            return ret;
        }

        if ("id".equals(fieldName)) {
            //move to next token
            int id = reader.nextInt();
            reader.nextName();
            String type = reader.nextString();

            reader.nextName();
            reader.beginArray();
            List<Expression> subExpressions = new ArrayList<>();
            while (reader.hasNext()) {
                subExpressions.add(readExpression(reader));
            }
            reader.endArray();
            reader.endObject();
            ret = new Calculation(id, CalculationType.fromName(type));
            ((Calculation) ret).addExpressions(subExpressions);
            return ret;
        }
        return null;
    }

    private Expression readNumber(JsonReader reader) throws IOException {
        Expression ret = new Number(reader.nextDouble());
        reader.endObject();
        return ret;
    }
}
