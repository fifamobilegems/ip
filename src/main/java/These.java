import java.util.Objects;
import java.util.Scanner;

public class These {
    static String[] task_list = new String[100];
    static int task_id = 0;

    public static void echo(String input) {
        Scanner sc = new Scanner(System.in);
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
        } else {
            System.out.println("added: " + input);
            task_list[task_id] = input;
            task_id++;
            String next = sc.nextLine();
            echo(next);
        }
    }

    public static void list() {
        for (int i = 0; i < task_id; i++) {
            System.out.println(i + ". " + task_list[i]);
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
