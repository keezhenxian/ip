import java.util.Scanner;

public class Bestbot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bestbot");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(line);
        }
    }
}


