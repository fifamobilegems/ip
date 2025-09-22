package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;

/**
 * Represents a Command that marks a specified task in the task list as done
 *
 * When executed, the {@code UnmarkCommand} parses user input to obtain the
 * index of the task, then changes the {@link Task}’s marked status from
 * true to false. If there is no index given, UnmarkCommand throws a {@link TheseException}.
 * If the task is already unmarked, the command still succeeds and returns the confirmation message.
 */
public class UnmarkCommand implements Command {
    private final These these;

    /**
     * Constructs a new UnmarkCommand associated with a given {@link These} instance.
     *
     * @param these the main application instance that provides
     * access to the task list, UI, and storage
     */
    public UnmarkCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }

    /**
     * Executes the unmark command by parsing the user input to obtain the task index
     * and unmarking that task as done. A success message is then displayed.
     *
     * @param input user input expected in the form {@code "unmark <task_number>"}
     * @return true after command runs successfully
     * @throws TheseException if the task number is missing
     */
    @Override
    public boolean run(String input) throws TheseException {
        // parse input and throw exception accordingly
        String[] parsedInput = input.split(" ", 2);
        if (parsedInput.length < 2 || parsedInput[1].trim().isEmpty()) {
            throw new TheseException("unmark needs a number");
        }

        int unmark_index = Integer.parseInt(parsedInput[1]);
        these.getTaskList().getTask(unmark_index).unmark();

        // output
        String msg = "OK, I've marked this task as not done yet:\n";
        these.getUi().showMessage(msg + these.getTaskList().getTask(unmark_index).toString());

        return true;
    }

}
