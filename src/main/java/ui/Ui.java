package ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner (System.in);
    }

    public void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello! I'm These\n" + "What can I do for you?";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
    }

    public void outro() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
    }

    public void showError(String msg) {
        System.out.println("Error: " + msg);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String readNext() {
        return sc.nextLine();
    }
}
