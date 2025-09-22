package commands;

import app.These;
import exceptions.TheseException;
import tasks.Task;

/**
 * Represents a command that deletes a single task from the task list.
 * Expects user input with an index to tell the system which task to delete
 */
public class DeleteCommand implements Command {
    private final These these;

    /**
     * Create a new DeleteCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public DeleteCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }

    /**
     * Executes command by parsing user input, validating presence of
     * index (of task to be deleted), and deleting task from the TaskList of these.
     *
     * @param input in the format 'delete n', where n is the index of the task to be deleted
     * @return true if command is executed successfully
     * @throws TheseException
     */
    @Override
    public boolean run(String input) throws TheseException {
        //exception
        String[] parsedInput = input.split(" ", 2);
        if (parsedInput.length < 2 || parsedInput[1].trim().isEmpty()) {
            throw new TheseException("you're deleting nothing");
        }

        int delIndex = Integer.parseInt(parsedInput[1]);
        Task t = these.getTaskList().deleteTask(delIndex);

        String msg = "Noted. I've removed this task:\n" + t.toString()
                + "\nNow you have " + (these.getTaskList().getId() - 1) + " tasks in the list";
        these.getUi().showMessage(msg);
        return true;
    }
}
