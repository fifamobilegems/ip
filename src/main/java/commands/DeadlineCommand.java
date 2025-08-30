package commands;

import exceptions.TheseException;
import tasks.Deadline;
import tasks.Task;
import app.These;

public class DeadlineCommand implements Command {
    private These these;

    public DeadlineCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new TheseException("your deadline has nothing");
        }

        String[] byPart = parts[1].split("/by");
        if (byPart.length < 2 || byPart[1].trim().isEmpty()) {
            throw new TheseException("your deadline needs to have /by field");
        }

        int task_id = these.getTask_id();

        Deadline deadline = new Deadline(byPart[0], false, task_id, byPart[1]);
        these.addTask(deadline);

        String msg = "Got it. I've added this task:\n"
                + deadline
                + "\nNow you have " + task_id + " tasks in the list.";
        System.out.println(msg);

        return true;
    }
}
