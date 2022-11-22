package xml.parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Multiplication extends AbstractExpression implements Expression {
    private final List<Expression> factors = new ArrayList<>();

    public Multiplication(int id, List<Expression> factors) {
        super(id);
        this.factors.addAll(factors);
    }

    @Override
    public double calculate() {
        Optional<Double> ret = factors.stream().map(Expression::calculate).reduce((d1, d2) -> d1 * d2);
        return ret.isEmpty() ? 1 : ret.get();
    }
}
