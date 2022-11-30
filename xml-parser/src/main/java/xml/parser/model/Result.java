package xml.parser.model;

public class Result {
    private final int id;
    private final double result;

    public Result(int id, double result) {
        this.id = id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public double getResult() {
        return result;
    }
}
