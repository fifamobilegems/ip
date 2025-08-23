import java.util.Objects;
import java.util.Scanner;

public class These {
    public static void echo(String input) {
        Scanner sc = new Scanner(System.in);
        String outro = "Bye. Hope to see you again soon!";
        if (Objects.equals(input, "bye")) {
            System.out.println(outro);
        } else {
            System.out.println(input);
            String next = sc.nextLine();
            echo(next);
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
