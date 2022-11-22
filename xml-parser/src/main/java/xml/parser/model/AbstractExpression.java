package xml.parser.model;

public abstract class AbstractExpression implements Expression {
    protected final int id;

    public AbstractExpression(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
