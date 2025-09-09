package bestbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task with a "from" and "to" date (Level-8 uses LocalDate).
 */
public class Event extends Task {
    /** Starting date of the event. */
    private final LocalDate from;

    /** Ending date of the event. */
    private final LocalDate to;

    /** Formatter for displaying dates as "MMM d yyyy" (e.g., Sep 10 2025). */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a new Event task with the given description and dates.
     *
     * @param description Description of the event task.
     * @param from Starting date in yyyy-MM-dd format.
     * @param to Ending date in yyyy-MM-dd format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public static Event fromSaveFormat(String[] parts) {
        // Format: E | <0/1> | description | yyyy-MM-dd | yyyy-MM-dd
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(FORMATTER)
                + " to: " + to.format(FORMATTER) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " | " + to;
        // Saved as yyyy-MM-dd
    }
}
