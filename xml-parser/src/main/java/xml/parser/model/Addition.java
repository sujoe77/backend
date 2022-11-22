package xml.parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Addition extends AbstractExpression implements Expression {
    private final List<Expression> items = new ArrayList<>();

    public Addition(int id) {
        this(id, new ArrayList<>());
    }

    public Addition(int id, List<Expression> items) {
        super(id);
        this.items.addAll(items);
    }

    @Override
    public double calculate() {
        Optional<Double> result = items.stream().map(Expression::calculate).reduce(Double::sum);
        return result.isEmpty() ? 0d : result.get();
    }

    @Override
    public int getId() {
        return 0;
    }
}
