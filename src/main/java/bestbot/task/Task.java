package bestbot.task;

import bestbot.exception.BestbotException;

/**
 * Represents a base class for tasks in the task list.
 * A task has a description and a completion status.
 */
public abstract class Task {

    /** Description of the task. */
    protected final String description;

    /** Indicates whether the task has been completed. */
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially not marked as done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return {@code "X"} if the task is done, {@code " "} otherwise.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task suitable for saving to storage.
     *
     * @return The string format of the task for saving.
     */
    public abstract String toSaveFormat();

    /**
     * Creates a {@code Task} object from its saved string representation.
     *
     * @param line The line containing the saved task data.
     * @return The parsed {@code Task}.
     * @throws BestbotException If the line cannot be parsed into a valid task.
     */
    public static Task fromSaveFormat(String line) throws BestbotException {
        // Placeholder implementation: actual parsing logic to be added.
        throw new BestbotException("Parsing from save not yet implemented.");
    }
}
