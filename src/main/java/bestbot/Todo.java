package bestbot;

/** Todo task. */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    protected char typeCode() {
        return 'T';
    }

    @Override
    protected String encodeBody() {
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

