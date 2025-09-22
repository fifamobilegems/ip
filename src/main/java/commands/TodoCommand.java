package commands;

import exceptions.TheseException;
import tasks.Todo;
import app.These;

/**
 * Represents a command that creates a new Todo task
 * in the task list of the {@link These} instance.
 * The command expects user input to be in the format of "todo <task name>"
 */
public class TodoCommand implements Command {
    private final These these;

    /**
     * Create a new DeadlineCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public TodoCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }


    @Override
    public boolean run(String input) throws TheseException {

        String[] parsedInput = input.split(" ", 2);
        if (parsedInput.length < 2) {
            throw new TheseException("your todo has nothing");
        }

        int nextId = these.getTaskList().getId();

        Todo todo = new Todo(parsedInput[1], false, these.getTaskList().getId());
        these.getTaskList().addTask(todo);

        String msg = "Got it. I've added this task:\n"
                + todo
                + "\nNow you have " + nextId + " tasks in the list.";
        these.getUi().showMessage(msg);

        return true;
    }
}
