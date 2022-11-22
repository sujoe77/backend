package xml.parser.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void calculate() {
        Expression addition = new Addition(0, List.of());
        assertEquals(0, addition.calculate());

        addition = new Addition(1, List.of(new Number(1d)));
        assertEquals(1d, addition.calculate());

        addition = new Addition(2, List.of(1d, 2d).stream().map(Number::new).collect(Collectors.toList()));
        assertEquals(3d, addition.calculate());
    }
}