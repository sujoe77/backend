package xml.parser.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xml.parser.model.CalculationType.*;

class CalculationTest {

    @Test
    void calculateAddition() {
        Expression addition = new Calculation(0, ADDITION);
        assertEquals(0, addition.calculate());

        addition = new Calculation(1, ADDITION);
        ((Calculation) addition).addExpressions(List.of(new Number(1d)));
        assertEquals(1d, addition.calculate());

        addition = new Calculation(2, ADDITION);
        ((Calculation) addition).addExpressions(List.of(1d, 2d).stream().map(Number::new).collect(Collectors.toList()));
        assertEquals(3d, addition.calculate());
    }

    @Test
    void calculateSubtraction() {
        Expression subtraction = new Calculation(0, SUBTRACTION){{
            addExpressions(List.of(new Number(5), new Number(2)));
        }};
        assertEquals(3, subtraction.calculate());
    }

    @Test
    void calculateMultiplication() {
        Expression multiplication = new Calculation(0, MULTIPLICATION){{
            addExpressions(List.of(new Number(5), new Number(2), new Number(10)));
        }};
        assertEquals(100, multiplication.calculate());
    }

    @Test
    void calculateDivision() {
        Expression division = new Calculation(0, DIVISION){{
            addExpressions(List.of(new Number(54), new Number(6)));
        }};
        assertEquals(9, division.calculate());
    }
}