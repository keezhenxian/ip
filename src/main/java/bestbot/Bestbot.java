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
        String cmd = parts[0];
        String args = parts[1];

        switch (cmd) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                return;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                }
                return;
            case "todo":
                if (args.isBlank()) {
                    throw new BestbotException("The description of a todo cannot be empty.");
                }
                tasks.add(new Todo(args));
                printAddedTask(tasks.get(tasks.size() - 1));
                return;
            case "deadline":
                if (!args.contains("/by")) {
                    throw new BestbotException("Deadline needs a description and /by <time>.");
                }
                String[] d = args.split("/by", 2);
                if (d[0].isBlank() || d[1].isBlank()) {
                    throw new BestbotException("Deadline needs a description and /by <time>.");
                }
                tasks.add(new Deadline(d[0].trim(), d[1].trim()));
                printAddedTask(tasks.get(tasks.size() - 1));
                return;
            case "event":
                if (!args.contains("/at")) {
                    throw new BestbotException("Event needs a description and /at <time>.");
                }
                String[] e = args.split("/at", 2);
                if (e[0].isBlank() || e[1].isBlank()) {
                    throw new BestbotException("Event needs a description and /at <time>.");
                }
                tasks.add(new Event(e[0].trim(), e[1].trim()));
                printAddedTask(tasks.get(tasks.size() - 1));
                return;
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
