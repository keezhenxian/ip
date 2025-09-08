package bestbot;

/** Deadline task with a "by" field (string for Level-7). */
public class Deadline extends Task {
    private final String by;

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
    protected char typeCode() {
        return 'D';
    }

    @Override
    protected String encodeBody() {
        return description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

