import java.util.*;

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
            } else {
                tasks.add(new Task(line));
                System.out.println("added: " + line);
            }
        }
    }
}

class Task {
    private boolean done;
    private final String description;

    public Task(String d) {
        this.description = d;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", description);
    }
}
