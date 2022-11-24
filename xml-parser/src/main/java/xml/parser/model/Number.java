package xml.parser.model;

public class Number implements Expression {
    private final double number;

    public Number(double number) {
        this.number = number;
    }

    @Override
    public double calculate() {
        return number;
    }
}