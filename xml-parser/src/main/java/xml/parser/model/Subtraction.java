package xml.parser.model;

public class Subtraction extends AbstractExpression implements Expression {
    private final Expression minuend;
    private final Expression subtrahend;

    public Subtraction(int id, Expression minuend, Expression subtrahend) {
        super(id);
        this.minuend = minuend;
        this.subtrahend = subtrahend;
    }

    @Override
    public double calculate() {
        return minuend.calculate() - subtrahend.calculate();
    }
}
