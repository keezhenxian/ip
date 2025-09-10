package bestbot;

import java.util.List;
import java.util.Scanner;
import bestbot.task.Task;

/**
 * Handles all user interaction (input/output).
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object to handle user input and output.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Prints the welcome message. */
    public void showWelcome() {
        System.out.println("Hello! I'm Bestbot");
        System.out.println("What can I do for you?");
    }

    /** Prints the exit message. */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /** Reads the next input line from the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Prints a divider line between UI outputs. */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to the user.
     *
     * @param message The error text.
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /** Informs the user that loading from file failed. */
    public void showLoadingError() {
        System.out.println("Error loading tasks, starting with an empty list.");
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Displays a message when a task has been added.
     *
     * @param task       The task that was added.
     * @param totalTasks The updated total number of tasks.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays a message when a task has been deleted.
     *
     * @param task       The task that was removed.
     * @param totalTasks The updated total number of tasks.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays a message when a task has been marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays the tasks matching a find keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        }
    }
}
