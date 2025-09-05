package commands;

import app.These;
import exceptions.TheseException;

/**
 * Represents a command that clears all tasks from the task list.
 */
public class ClearCommand implements Command {
    private These these;

    /**
     * Create new ClearCommand associated with a These instance
     *
     * @param these
     */
    public ClearCommand(These these) {
        this.these = these;
    }

    /**
     * Removes all tasks from TaskList associated with the These instance,
     * updates Storage with the cleared TaskList, and shows message upon completion
     *
     * @param input
     * @return true if command is executed successfully
     * @throws TheseException
     */
    @Override
    public boolean run(String input) throws TheseException {
        these.getTaskList().clear();
        these.getStorage().updateTasks(these.getTaskList());
        these.getUi().showMessage("All tasks cleared!");
        return true;
    }
}
