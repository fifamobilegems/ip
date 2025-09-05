package commands;

import exceptions.TheseException;
import tasks.Task;
import tasks.Todo;
import app.These;

/**
 * Represents a command that creates a new Todo task
 * in the task list of the {@link These} instance.
 * The command expects user input to be in the format of "todo <task name>"
 */
public class TodoCommand implements Command {
    private These these;

    /**
     * Create a new DeadlineCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public TodoCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new TheseException("your todo has nothing");
        }

        int task_id = these.getTaskList().getId();

        Todo todo = new Todo(parts[1], false, these.getTaskList().getId());
        these.getTaskList().addTask(todo);

        String msg = "Got it. I've added this task:\n"
                + todo
                + "\nNow you have " + task_id + " tasks in the list.";
        these.getUi().showMessage(msg);

        return true;
    }
}
