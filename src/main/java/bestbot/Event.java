package bestbot;

/**
 * Represents an event task that happens between two times.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an Event with description, start time, and end time.
     *
     * @param description description of the event
     * @param from start time
     * @param to end time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }
}
