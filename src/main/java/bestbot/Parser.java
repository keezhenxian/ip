package bestbot;

/**
 * Utility class for parsing user input.
 */
public final class Parser {
    private Parser() { }

    public static String[] splitFirstWord(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return new String[] {"", ""};
        }
        String[] parts = trimmed.split("\\s+", 2);
        return parts.length == 1
                ? new String[] {parts[0], ""}
                : parts;
    }
}

