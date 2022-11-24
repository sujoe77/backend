package xml.parser.model.xml;

import java.util.Objects;

public class XmlEvent {
    private final int id;
    private final EventType type;
    private final String text;

    public XmlEvent(int id, EventType eventType, String text) {
        this.id = id;
        this.type = eventType;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public EventType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlEvent xmlEvent = (XmlEvent) o;
        return id == xmlEvent.id && type == xmlEvent.type && Objects.equals(text, xmlEvent.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, text);
    }

    @Override
    public String toString() {
        return "XmlEvent{" +
                "id=" + id +
                ", type=" + type +
                ", text='" + text + '\'' +
                '}';
    }
}
