import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bestbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bestbot");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                }

            } else if (line.startsWith("mark")) {
                int idx = Integer.parseInt(line.substring(5))- 1;
                tasks.get(idx).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(idx));

            } else if (line.startsWith("unmark")) {
                int idx = Integer.parseInt(line.substring(7))- 1;
                tasks.get(idx).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(idx));

            } else if (line.startsWith("todo")) {
                String description = line.substring(5);
                Task t = new Todo(description);
                tasks.add(t);
                printAddedTask(t, tasks.size());

            } else if (line.startsWith("deadline ")) {
                String[] parts = line.substring(9).split(" /by ", 2);
                Task t = new Deadline(parts[0], parts[1]);
                tasks.add(t);
                printAddedTask(t, tasks.size());

            } else if (line.startsWith("event ")) {
                String[] parts = line.substring(6).split(" /from ", 2);
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ", 2);
                Task t = new Event(description, timeParts[0], timeParts[1]);
                tasks.add(t);
                printAddedTask(t, tasks.size());

            } else {
                tasks.add(new Task(line));
                System.out.println("added: " + line);
            }
        }
    }

    private static void printAddedTask(Task t, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.printf(" Now you have %d tasks in the list.%n", size);
    }
}

class Task {
    protected boolean done;
    protected final String description;

    public Task(String d) {
        this.description = d;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getStatusIcon() {
        return done ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

class Todo extends Task {
    public Todo(String d) {
        super(d);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String d, String by) {
        super(d);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String d, String from, String to) {
        super(d);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

