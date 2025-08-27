import java.util.Objects;
import java.util.Scanner;

public class These {
    static Task[] task_list = new Task[100];
    static int task_id = 1;
    static final Scanner sc = new Scanner(System.in);

    public static void echo(String input) {
        String intro = "Hello! I'm These\n" + "What can I do for you?";
        String outro = "Bye. Hope to see you again soon!";


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
            System.out.println(msg + task_list[mark_index].toString());

            String next = sc.nextLine();
            echo(next);

        } else if (input.startsWith("unmark ")) {
            // parse input and unmark
            String[] parts = input.split(" ");
            int unmark_index = Integer.parseInt(parts[1]);
            task_list[unmark_index].unmark();

            // output
            String msg = "OK, I've marked this task as not done yet:\n";
            System.out.println(msg + task_list[unmark_index].toString());

            String next = sc.nextLine();
            echo(next);

        } else {
            // task
            String[] parts = input.split(" ", 2);

            if (input.startsWith("todo")) {
                Todo todo = new Todo(parts[1], false, task_id);
                task_list[task_id] = todo;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + todo
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);
            } else if (input.startsWith("deadline")) {
                String[] byPart = parts[1].split("/by");
                Deadline deadline = new Deadline(byPart[0], false, task_id, byPart[1]);
                task_list[task_id] = deadline;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + deadline
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);
            } else {
                String[] fromPart = parts[1].split("/from");
                String[] toPart = fromPart[1].split("/to");
                Event event = new Event(fromPart[0], false, task_id, toPart[0], toPart[1]);
                task_list[task_id] = event;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + event
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);
            }

            String next = sc.nextLine();
            echo(next);
        }
    }

    public static String getTaskCount() {
        return String.valueOf(task_id-1);
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < task_id; i++) {
            Task these = task_list[i];
            System.out.println(these.getId() + "." + these);
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
