package commands;

import app.These;
import exceptions.TheseException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import tasks.Deadline;

/**
 * Represents a command that creates a new Deadline task
 * in the task list of the {@link These} instance.
 * The command expects user input to be in the format of 'deadline /by yyyy-mm-dd'
 */
public class DeadlineCommand implements Command {
    private final These these;

    /**
     * Create a new DeadlineCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public DeadlineCommand(These these) {
        this.these = these;
    }

    /**
     * Execute command by parsing user input, validating /by field, creating
     * new Deadline instance and adding it to task list.
     *
     * @param input
     * @return true if command is executed successfully
     * @throws TheseException
     */
    @Override
    public boolean run(String input) throws TheseException {

        String[] parsedInput = input.split(" ", 2);
        if (parsedInput.length < 2) {
            throw new TheseException("your deadline has nothing");
        }

        String[] byPart = parsedInput[1].split("/by ");
        if (byPart.length < 2 || byPart[1].trim().isEmpty()) {
            throw new TheseException("your deadline needs to have /by field");
        }

        int nextId = these.getTaskList().getId();
        String byDate = byPart[1];
        LocalDate by;

        try {
            by = LocalDate.parse(byDate.trim());
        } catch (DateTimeParseException e) {
            throw new TheseException("your /by field needs to be in the format yyyy-mm-dd");
        }

        Deadline deadline = new Deadline(byPart[0], false, nextId, by);
        these.getTaskList().addTask(deadline);

        String msg = "Got it. I've added this task:\n"
                + deadline
                + "\nNow you have " + nextId + " tasks in the list.";
        these.getUi().showMessage(msg);

        return true;
    }
}
