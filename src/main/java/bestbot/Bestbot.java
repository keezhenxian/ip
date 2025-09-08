package bestbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for Bestbot application.
 */
public class Bestbot {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Bestbot");
        System.out.println("What can I do for you?");
        // Load tasks from storage at startup
        tasks.addAll(Storage.load());

        while (true) {
            String line = sc.nextLine();
            try {
                handle(line);
            } catch (BestbotException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
        }
    }

    private static void handle(String line) throws BestbotException {
        String[] parts = Parser.splitFirstWord(line);
        Command cmd = Command.fromString(parts[0]);
        String args = parts[1];

        switch (cmd) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                return;

            case LIST:
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                }
                return;

            case TODO:
                if (args.isBlank()) {
                    throw new BestbotException("The description of a todo cannot be empty.");
                }
                Task todo = new Todo(args);
                tasks.add(todo);
                printAddedTask(todo);
                Storage.save(tasks);
                return;

            case DEADLINE:
                if (!args.contains("/by")) {
                    throw new BestbotException("Deadline needs a description and /by <time>.");
                }
                String[] d = args.split("/by", 2);
                if (d[0].isBlank() || d[1].isBlank()) {
                    throw new BestbotException("Deadline needs a description and /by <time>.");
                }
                Task deadline = new Deadline(d[0].trim(), d[1].trim());
                tasks.add(deadline);
                printAddedTask(deadline);
                Storage.save(tasks);
                return;

            case EVENT:
                if (!args.contains("/from") || !args.contains("/to")) {
                    throw new BestbotException("Event needs a description, /from <time>, and /to <time>.");
                }
                String[] eventParts = args.split("/from", 2);
                String description = eventParts[0].trim();
                String[] timeParts = eventParts[1].split("/to", 2);

                if (description.isBlank() || timeParts[0].isBlank() || timeParts[1].isBlank()) {
                    throw new BestbotException("Event needs a description, /from <time>, and /to <time>.");
                }

                Task event = new Event(description, timeParts[0].trim(), timeParts[1].trim());
                tasks.add(event);
                printAddedTask(event);
                Storage.save(tasks);
                return;

            case DELETE:
                if (args.isBlank()) {
                    throw new BestbotException("Index required for delete.");
                }
                int idx;
                try {
                    idx = Integer.parseInt(args.trim());
                } catch (NumberFormatException nfe) {
                    throw new BestbotException("Index must be a number.");
                }
                if (idx < 1 || idx > tasks.size()) {
                    throw new BestbotException("Index out of range.");
                }
                Task removed = tasks.remove(idx - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removed);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                Storage.save(tasks);
                return;

            case MARK:
                if (args.isBlank()) {
                    throw new BestbotException("Please specify which task number to mark.");
                }
                int markIndex = Integer.parseInt(args.trim()) - 1;
                if (markIndex < 0 || markIndex >= tasks.size()) {
                    throw new BestbotException("Task number is invalid.");
                }
                tasks.get(markIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(markIndex));
                Storage.save(tasks);
                return;

            case UNMARK:
                if (args.isBlank()) {
                    throw new BestbotException("Please specify which task number to unmark.");
                }
                int unmarkIndex = Integer.parseInt(args.trim()) - 1;
                if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                    throw new BestbotException("Task number is invalid.");
                }
                tasks.get(unmarkIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(unmarkIndex));
                Storage.save(tasks);
                return;

            case UNKNOWN:
            default:
                throw new BestbotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void printAddedTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }
}

