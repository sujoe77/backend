package xml.parser.model;

public class Number extends AbstractExpression implements Expression {
    private final double number;

    public Number(double number) {
        super(-1);
        this.number = number;
    }

    @Override
    public double calculate() {
        return number;
    }
}
