package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;

public class UnmarkCommand implements Command {
    private These these;

    public UnmarkCommand(These these) {
        this.these = these;
    }

    public boolean run(String input) throws TheseException {
        // parse input and throw exception accordingly
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TheseException("unmark needs a number");
        }

        int unmark_index = Integer.parseInt(parts[1]);
        these.getTaskList().getTask(unmark_index).unmark();

        // output
        String msg = "OK, I've marked this task as not done yet:\n";
        these.getUi().showMessage(msg + these.getTaskList().getTask(unmark_index).toString());

        return true;
    }

}
