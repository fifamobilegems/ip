package commands;

import exceptions.TheseException;
import tasks.Event;
import tasks.Task;
import app.These;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that creates a new Event task.
 * The command expects user input to be in the format of 'event /from yyyy-mm-dd /by yyyy-mm-dd'
 */
public class EventCommand implements Command {
    private These these;

    /**
     * Create a new EventCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public EventCommand(These these) {
        this.these = these;
    }

    /**
     * Executes the event command by parsing the user input, validating
     * the {@code /from} and {@code /to} fields, creating a new {@link Event} task,
     * and adding it to the task list.
     *
     * @param input the raw user input string containing the event description,
     *              start date, and end date (expected format:
     *              {@code "event <description> /from yyyy-mm-dd /to yyyy-mm-dd"})
     * @return {@code true} if the command executes successfully
     * @throws TheseException if the description is missing, or if either
     *                        {@code /from} or {@code /to} fields are missing,
     *                        or if date format is invalid
     */
    @Override
    public boolean run(String input) throws TheseException {

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new TheseException("your event has nothing");
        }

        String[] fromPart = parts[1].split("/from ");
        if (fromPart.length < 2) {
            throw new TheseException("your event needs to have /from field");
        }

        String[] toPart = fromPart[1].split("/to ");
        if (toPart.length < 2) {
            throw new TheseException("your event needs to have /to field");
        }

        int task_id = these.getTaskList().getId();
        try {
            LocalDate from = LocalDate.parse(toPart[0].trim());
            LocalDate to = LocalDate.parse(toPart[1].trim());

            Event event = new Event(fromPart[0], false, task_id, from, to);
            these.getTaskList().addTask(event);

            String msg = "Got it. I've added this task:\n"
                    + event
                    + "\nNow you have " + task_id + " tasks in the list.";
            these.getUi().showMessage(msg);
        } catch (DateTimeParseException e) {
            throw new TheseException("your /from and /to fields need to be in the format yyyy-mm-dd");
        }

        return true;
    }
}
