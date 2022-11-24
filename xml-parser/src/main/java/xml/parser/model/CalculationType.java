package xml.parser.model;

public enum CalculationType {
    ADDITION("addition"), SUBTRACTION("subtraction"), MULTIPLICATION("multiplication"), DIVISION("division");

    private final String tagName;

    CalculationType(String tagName) {
        this.tagName = tagName;
    }

    public static CalculationType fromName(String name) {
        for (CalculationType type : values()) {
            if (type.tagName.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
