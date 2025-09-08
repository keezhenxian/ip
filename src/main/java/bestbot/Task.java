package bestbot;

/**
 * Represents a general task in Bestbot.
 */
public class Task {
    protected boolean done;
    protected final String description;

    /**
     * Creates a new task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, otherwise a space.
     */
    public String getStatusIcon() {
        return done ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}



