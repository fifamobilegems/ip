import java.util.Objects;
import java.util.Scanner;

public class These {
    static Task[] task_list = new Task[100];
    static int task_id = 1;
    static final Scanner sc = new Scanner(System.in);

    public static boolean echo (String input) throws TheseException {
        String intro = "Hello! I'm These\n" + "What can I do for you?";
        String outro = "Bye. Hope to see you again soon!";

        String[] parts = input.split(" ", 2);

        if (Objects.equals(input, intro)) {
            System.out.println(intro);
            return true;
        } else if (Objects.equals(input, "bye")) {
            System.out.println(outro);
            return false;
        } else if (Objects.equals(input, "list")) {
            list();
            return true;
        } else if (input.startsWith("delete")) {
            //exception
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new TheseException("you're deleting nothing");
            }

            int del_index = Integer.parseInt(parts[1]);
            Task t = task_list[del_index];
            for (int i = del_index; i < task_id-1; i++) {
                task_list[i] = task_list[i+1];
                task_list[i].dropId();
            }
            task_id--;
            String msg = "Noted. I've removed this task:\n" + t.toString()
                    + "\nNow you have " + getTaskCount() + " tasks in the list";
            System.out.println(msg);
            return true;

        }
        else if (input.startsWith("mark")) {

            // catch shit input
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new TheseException("mark needs a number");
            }

            int mark_index = Integer.parseInt(parts[1]);
            task_list[mark_index].mark();

            // output
            String msg = "Nice! I've marked this task as done:\n";
            System.out.println(msg + task_list[mark_index].toString());

            return true;

        } else if (input.startsWith("unmark")) {

            // exception
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new TheseException("unmark needs a number");
            }

            int unmark_index = Integer.parseInt(parts[1]);
            task_list[unmark_index].unmark();

            // output
            String msg = "OK, I've marked this task as not done yet:\n";
            System.out.println(msg + task_list[unmark_index].toString());

            String next = sc.nextLine();
            echo(next);

        } else {

            if (input.startsWith("todo")) {

                if (parts.length < 2) {
                    throw new TheseException("you're todoing nothing");
                }

                Todo todo = new Todo(parts[1], false, task_id);
                task_list[task_id] = todo;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + todo
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);

            } else if (input.startsWith("deadline")) {

                String[] byPart = parts[1].split("/by");
                if (byPart.length < 2 || byPart[1].trim().isEmpty()) {
                    throw new TheseException("your deadline needs to have /by field");
                }
                Deadline deadline = new Deadline(byPart[0], false, task_id, byPart[1]);
                task_list[task_id] = deadline;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + deadline
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);
            } else if (input.startsWith("event")) {

                String[] fromPart = parts[1].split("/from");
                if (fromPart.length < 2) {
                    throw new TheseException("your event needs to have /from field");
                }

                String[] toPart = fromPart[1].split("/to");
                if (toPart.length < 2) {
                    throw new TheseException("your event needs to have /to field");
                }

                Event event = new Event(fromPart[0], false, task_id, toPart[0], toPart[1]);
                task_list[task_id] = event;
                task_id++;

                String msg = "Got it. I've added this task:\n"
                        + event
                        + "\nNow you have " + getTaskCount() + " tasks in the list.";
                System.out.println(msg);
            } else {
                throw new TheseException("what are you even saying");
            }

            return true;
        }
        return true;
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
        System.out.println(intro);

        while (true) {
            String next = sc.nextLine();
            try {
                boolean exit = echo(next);
                if (!exit) break;
            } catch (TheseException e) {
                System.out.println("woah " + e.getMessage());
            }
        }
    }
}
