package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;

public class MarkCommand implements Command {
    private These these;

    public MarkCommand(These these) {
        this.these = these;
    }

    public boolean run(String input) throws TheseException {
        // parse input and throw exception accordingly
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TheseException("mark needs a number");
        }

        int mark_index = Integer.parseInt(parts[1]);
        these.getTask(mark_index).mark();

        // output
        String msg = "Nice! I've marked this task as done:\n";
        System.out.println(msg + these.getTask(mark_index).toString());

        return true;
    }

}
