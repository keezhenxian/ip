package bestbot;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    private final String at;

    /**
     * Creates a new Event task with the given description and time.
     *
     * @param description Description of the event.
     * @param at Time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
