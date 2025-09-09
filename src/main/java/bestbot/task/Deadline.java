package bestbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Task with a deadline ("by" date).
 */
public class Deadline extends Task {
    private final LocalDate by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a Deadline task.
     *
     * @param description Task description.
     * @param by          Deadline in yyyy-MM-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
