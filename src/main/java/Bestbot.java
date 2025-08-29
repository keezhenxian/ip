import java.util.*;

public class Bestbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bestbot");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                }
            } else {
                tasks.add(line);
                System.out.println("added: " + line);
            }
        }
    }
}
