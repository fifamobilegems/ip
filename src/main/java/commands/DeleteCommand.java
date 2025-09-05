package commands;

import tasks.Task;
import exceptions.TheseException;
import app.These;

/**
 * Represents a command that deletes a single task from the task list.
 * Expects user input with an index to tell the system which task to delete
 */
public class DeleteCommand implements Command {
    private These these;

    /**
     * Create a new DeleteCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public DeleteCommand(These these) {
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
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TheseException("you're deleting nothing");
        }

        int del_index = Integer.parseInt(parts[1]);
        Task t = these.getTaskList().deleteTask(del_index);

        String msg = "Noted. I've removed this task:\n" + t.toString()
                + "\nNow you have " + (these.getTaskList().getId()-1) + " tasks in the list";
        these.getUi().showMessage(msg);
        return true;
    }
}
