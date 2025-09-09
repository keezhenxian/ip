package bestbot;

/**
 * Todo task with only a description (no date/time).
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    public static Todo fromSaveFormat(String[] parts) {
        // Format: T | <0/1> | description
        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}



