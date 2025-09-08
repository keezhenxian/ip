package bestbot;

/**
 * Base type for all tasks.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
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
        return isDone ? "X" : " ";
    }

    /** Single-character type code: 'T', 'D', or 'E'. */
    protected abstract char typeCode();

    /** Body part of the encode string excluding type and done flag. */
    protected abstract String encodeBody();

    /** Encode to a line like: T | 1 | read book  OR  D | 0 | desc | by  OR  E | 1 | desc | from | to */
    public String encode() {
        return typeCode() + " | " + (isDone ? "1" : "0") + " | " + encodeBody();
    }

    /**
     * Decode one line into a Task.
     * Accepts lines of forms:
     *  - T | {0|1} | description
     *  - D | {0|1} | description | by
     *  - E | {0|1} | description | from | to
     */
    public static Task decode(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }
        // split on " | " allowing extra spaces around '|'
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        String doneFlag = parts[1];
        String desc = parts[2];

        Task t;
        switch (type) {
            case "T":
                t = new Todo(desc);
                break;
            case "D":
                if (parts.length < 4) return null;
                t = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length < 5) return null;
                t = new Event(desc, parts[3], parts[4]);
                break;
            default:
                return null;
        }

        if ("1".equals(doneFlag)) {
            t.markAsDone();
        } else {
            t.markAsNotDone();
        }
        return t;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}



