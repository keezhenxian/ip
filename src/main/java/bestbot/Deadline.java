package bestbot;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected final String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description Description of the deadline task.
     * @param by Deadline string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
