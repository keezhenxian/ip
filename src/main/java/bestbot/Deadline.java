package bestbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that must be completed by a specific date.
 * In Level-8, the deadline date is stored as a LocalDate
 * and printed in a human-friendly format (e.g., Sep 10 2025).
 */
public class Deadline extends Task {
    /** The deadline date for the task. */
    private final LocalDate by;

    /**
     * Creates a new Deadline task from a description and a date string.
     * The date string must be in ISO format: yyyy-MM-dd.
     *
     * @param description Description of the deadline task.
     * @param by Date string in yyyy-MM-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Creates a Deadline task with all fields, used when loading from storage.
     *
     * @param description Description of the deadline task.
     * @param by Date of the deadline.
     * @param isDone Whether the task is marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Deadline task
     * in a user-friendly format, with the deadline date
     * displayed as "MMM d yyyy" (e.g., Sep 10 2025).
     *
     * @return Formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a
     * machine-friendly format for saving to disk.
     *
     * @return Save format string.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }

    /**
     * Reconstructs a Deadline task from its saved string parts.
     *
     * @param parts The split save string: ["D", "1/0", "description", "yyyy-MM-dd"].
     * @return Deadline object.
     */
    public static Deadline fromSaveFormat(String[] parts) {
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        LocalDate by = LocalDate.parse(parts[3]);
        return new Deadline(description, by, isDone);
    }
}
