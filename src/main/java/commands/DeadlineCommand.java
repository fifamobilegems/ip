package commands;

import exceptions.TheseException;
import tasks.Deadline;
import app.These;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

        String[] byPart = parts[1].split("/by ");
        if (byPart.length < 2 || byPart[1].trim().isEmpty()) {
            throw new TheseException("your deadline needs to have /by field");
        }

        int task_id = these.getTaskList().getId();
        String byDate = byPart[1];
        LocalDate by;
        try {
            by = LocalDate.parse(byDate.trim());
        } catch (DateTimeParseException e) {
            throw new TheseException("your /by field needs to be in the format yyyy-mm-dd");
        }

        Deadline deadline = new Deadline(byPart[0], false, task_id, by);
        these.getTaskList().addTask(deadline);

        String msg = "Got it. I've added this task:\n"
                + deadline
                + "\nNow you have " + task_id + " tasks in the list.";
        these.getUi().showMessage(msg);

        return true;
    }
}
