package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;

/**
 * Represents a Command that marks a specified task in the task list as done
 *
 * When executed, the {@code MarkCommand} parses user input to obtain the
 * index of the task, then changes the {@link Task}’s marked status from
 * false to true. If there is no index given, MarkCommand throws a {@link TheseException}.
 * If the task is already marked, the command
 * still succeeds and returns the confirmation message.
 */
public class MarkCommand implements Command {
    private final These these;

    /**
     * Constructs a new MarkCommand associated with a given {@link These} instance.
     *
     * @param these the main application instance that provides
     * access to the task list, UI, and storage
     */
    public MarkCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }

    /**
     * Executes the mark command by parsing the user input to obtain the task index
     * and marking that task as done. A success message is then displayed.
     *
     * @param input user input expected in the form {@code "mark <task_number>"}
     * @return true after command runs successfully
     * @throws TheseException if the task number is missing
     */
    public boolean run(String input) throws TheseException {
        // parse input and throw exception accordingly
        String[] parsedInput = input.split(" ", 2);
        if (parsedInput.length < 2 || parsedInput[1].trim().isEmpty()) {
            throw new TheseException("mark needs a number");
        }

        int markIndex = Integer.parseInt(parsedInput[1]);
        these.getTaskList().getTask(markIndex).mark();

        // output
        String msg = "Nice! I've marked this task as done:\n";
        these.getUi().showMessage(msg + these.getTaskList().getTask(markIndex).toString());

        return true;
    }

}
