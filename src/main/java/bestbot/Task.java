package bestbot;

/**
 * Abstract base class for all types of tasks (Todo, Deadline, Event).
 * Provides common fields and methods for handling task status.
 */
public abstract class Task {
    /** Description of the task. */
    protected final String description;

    /** Whether the task is marked as done. */
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * Initially, the task is not marked as done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns an "X" if the task is done, otherwise a space.
     *
     * @return "X" if done, " " if not done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts this task into a savable string format for Storage.
     * Subclasses must implement their own format.
     *
     * @return String representation for saving.
     */
    public abstract String toSaveFormat();
}
