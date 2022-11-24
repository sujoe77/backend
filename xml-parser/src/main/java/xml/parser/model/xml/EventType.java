package xml.parser.model.xml;

public enum EventType {
    START_ELEMENT(1), END_ELEMENT(2), END_DOCUMENT(8), CHARACTERS(4);

    private final int IntValue;

    EventType(int intValue) {
        IntValue = intValue;
    }

    public static EventType fromInt(int value) {
        for (EventType type : values()) {
            if (type.IntValue == value) {
                return type;
            }
        }
        return null;
    }
}
