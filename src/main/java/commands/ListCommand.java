package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;
import TaskList.TaskList;

/**
 * Represents a command that outputs all the tasks in the TaskList
 * sequentially based on the order they were added in
 */
public class ListCommand implements Command {
    private These these;

    /**
     * Create a new ListCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public ListCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }

    /**
     * On execution, ListCommand iterates over all tasks in the {@link TaskList},
     * and displays each task to the user in a numbered order, using {@link Task} toString()'s format
     *
     * @param input expects only the input "list"
     * @return true once command executes successfully
     * @throws TheseException not thrown in this method, but declared to
     * align with the implementation in the {@link Command} interface
     */
    public boolean run(String input) throws TheseException {

        assert input != null : "input must not be null";
        assert input.trim().equals("list") : "ListCommand must be invoked with 'list'";

        TaskList taskList = these.getTaskList();
        int taskId = these.getTaskList().getId();
        assert taskId >= 1 : "taskId should start at 1";

        String msg = "Here are the tasks in your list:";
        these.getUi().showMessage(msg);
        for (int i = 1; i < taskId; i++) {
            Task task = taskList.getTask(i);
            assert task != null : "Task " + i + " should exist";
            these.getUi().showMessage(task.getId() + "." + task);
        }
        return true;
    }
}
