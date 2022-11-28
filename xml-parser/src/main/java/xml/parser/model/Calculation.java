package xml.parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Calculation implements Expression {
    private final int id;
    private final CalculationType type;
    private final List<Expression> subExpressions = new ArrayList<>();

    public Calculation(int id, CalculationType type) {
        this.id = id;
        this.type = type;
    }

    public void addExpressions(List<Expression> expressions) {
        subExpressions.addAll(expressions);
    }

    @Override
    public double calculate() {
        if (type == CalculationType.ADDITION) {
            Optional<Double> result = subExpressions.stream().map(Expression::calculate).reduce(Double::sum);
            return result.isEmpty() ? 0d : result.get();
        } else if (type == CalculationType.SUBTRACTION) {
            return subExpressions.get(0).calculate() - subExpressions.get(1).calculate();
        } else if (type == CalculationType.MULTIPLICATION) {
            Optional<Double> result = subExpressions.stream().map(Expression::calculate).reduce((d1, d2) -> d1 * d2);
            return result.isEmpty() ? 1d : result.get();
        } else if (type == CalculationType.DIVISION) {
            return subExpressions.get(0).calculate() / subExpressions.get(1).calculate();
        } else if (type == CalculationType.POWER) {
            return Math.pow(subExpressions.get(0).calculate(), subExpressions.get(1).calculate());
        }
        throw new IllegalArgumentException();
    }

    public int getId() {
        return id;
    }

    public CalculationType getType() {
        return type;
    }

    public List<Expression> getSubExpressions() {
        return subExpressions;
    }
}
