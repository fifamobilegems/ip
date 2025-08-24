import java.util.Objects;
import java.util.Scanner;

public class These {
    static Task[] task_list = new Task[100];
    static int task_id = 0;
    static final Scanner sc = new Scanner(System.in);

    public static void echo(String input) {
        String intro = "Hello! I'm These\n" + "What can I do for you?";
        String outro = "Bye. Hope to see you again soon!";

        Task t = new Task(input, false, task_id);

        if (Objects.equals(input, intro)) {
            System.out.println(intro);
            String next = sc.nextLine();
            echo(next);
        } else if (Objects.equals(input, "bye")) {
            System.out.println(outro);
        } else if (Objects.equals(input, "list")) {
            list();
            String next = sc.nextLine();
            echo(next);
        } else if (input.startsWith("mark ")) {
            // parse input and mark
            String[] parts = input.split(" ");
            int mark_index = Integer.parseInt(parts[1]);
            task_list[mark_index].mark();

            // output
            String msg = "Nice! I've marked this task as done:\n";
            System.out.println(msg + "[X] " + task_list[mark_index].getName());

            String next = sc.nextLine();
            echo(next);

        } else if (input.startsWith("unmark ")) {
            // parse input and unmark
            String[] parts = input.split(" ");
            int unmark_index = Integer.parseInt(parts[1]);
            task_list[unmark_index].unmark();

            // output
            String msg = "OK, I've marked this task as not done yet:\n";
            System.out.println(msg + "[ ] " + task_list[unmark_index].getName());

            String next = sc.nextLine();
            echo(next);

        } else {
            System.out.println("added: " + input);
            task_list[task_id] = t;
            task_id++;
            String next = sc.nextLine();
            echo(next);
        }
    }

    public static void list() {
        for (int i = 0; i < task_id; i++) {
            System.out.println(task_list[i].toString());
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello! I'm These\n" + "What can I do for you?";

        System.out.println("Hello from\n" + logo);
        echo(intro);
    }
}
