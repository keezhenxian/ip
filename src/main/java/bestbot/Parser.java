package bestbot;

import bestbot.command.*;
import bestbot.exception.BestbotException;

/**
 * Parses user input strings into Command objects.
 */
public class Parser {
    public static Command parse(String fullCommand) throws BestbotException {
        String[] parts = fullCommand.trim().split(" ", 2);
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "done":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                return new DoneCommand(Integer.parseInt(parts[1]));

            case "delete":
                if (parts.length < 2) throw new BestbotException("Please specify a task number.");
                return new DeleteCommand(Integer.parseInt(parts[1]));

            case "todo":
                if (parts.length < 2) throw new BestbotException("The description of a todo cannot be empty.");
                return new AddTodoCommand(parts[1]);

            case "deadline":
                if (parts.length < 2 || !parts[1].contains("/by")) {
                    throw new BestbotException("Deadline needs description and /by <date>.");
                }
                String[] deadlineParts = parts[1].split("/by", 2);
                return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());

            case "event":
                if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new BestbotException("Event needs description, /from <time>, /to <time>.");
                }
                String[] eParts = parts[1].split("/from", 2);
                String desc = eParts[0].trim();
                String[] times = eParts[1].split("/to", 2);
                return new AddEventCommand(desc, times[0].trim(), times[1].trim());

            default:
                throw new BestbotException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
