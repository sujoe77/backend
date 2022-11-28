package xml.parser.model;

public enum CalculationType {
    ADDITION("addition"), SUBTRACTION("subtraction"), MULTIPLICATION("multiplication"), DIVISION("division"), POWER("power");

    private final String tagName;

    CalculationType(String tagName) {
        this.tagName = tagName;
    }

    public static CalculationType fromName(String name) {
        for (CalculationType type : values()) {
            if (type.tagName.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
