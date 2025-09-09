package bestbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task with a "by" date (Level-8 uses LocalDate).
 */
public class Deadline extends Task {
    /** Deadline date in LocalDate format. */
    private final LocalDate by;

    /** Formatter for displaying dates as "MMM d yyyy" (e.g., Sep 10 2025). */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a new Deadline task with the given description and deadline date.
     *
     * @param description Description of the deadline task.
     * @param by Deadline string in yyyy-MM-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public static Deadline fromSaveFormat(String[] parts) {
        // Format: D | <0/1> | description | yyyy-MM-dd
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
        // Saved as yyyy-MM-dd
    }
}

