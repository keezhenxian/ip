package bestbot;

/**
 * Represents the set of valid commands for Bestbot.
 */
public enum Command {
    BYE,
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    UNKNOWN;

    /**
     * Parses a string into a Command enum.
     * Returns UNKNOWN if the command is not recognized.
     */
    public static Command fromString(String input) {
        switch (input.toLowerCase()) {
            case "bye": return BYE;
            case "list": return LIST;
            case "todo": return TODO;
            case "deadline": return DEADLINE;
            case "event": return EVENT;
            case "mark": return MARK;
            case "unmark": return UNMARK;
            case "delete": return DELETE;
            default: return UNKNOWN;
        }
    }
}
