package bestbot;

/**
 * Base class for tasks.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

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

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    // Saving/loading helpers
    public abstract String toSaveFormat();

    public static Task fromSaveFormat(String line) throws BestbotException {
        // Parse logic depending on saved format
        // Placeholder implementation
        throw new BestbotException("Parsing from save not yet implemented.");
    }
}
