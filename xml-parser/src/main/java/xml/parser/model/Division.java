package xml.parser.model;

public  class Division extends AbstractExpression implements Expression {
    private final Expression dividend;
    private final Expression divisor;

    public Division(int id, Expression dividend, Expression divisor) {
        super(id);
        this.dividend = dividend;
        this.divisor = divisor;
        if (divisor.calculate() == 0d) {
            throw new IllegalArgumentException("divisor can not be 0!");
        }
    }

    @Override
    public double calculate() {
        return dividend.calculate() / divisor.calculate();
    }
}
