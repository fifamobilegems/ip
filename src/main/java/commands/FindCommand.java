package commands;

import TaskList.TaskList;
import app.These;
import exceptions.TheseException;
import tasks.Task;

/**
 * Represents a command that takes in user input in the format "find <keyword>"
 * and returns a list of tasks with the matching keyword
 */
public class FindCommand implements Command {
    private These these;

    /**
     * Create a new FindCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public FindCommand(These these) {
        this.these = these;
    }

    /**
     * Executes the find command to search for tasks matching the given keyword.
     * The method will display a list of tasks whose names contain the keyword.
     * If no matching tasks are found, a message indicating no results will be shown.
     *
     * @param input the user input in the format "find <keyword>", where <keyword> is the search term.
     * @return true always.
     * @throws TheseException if empty find command is given
     */
    @Override
    public boolean run(String input) throws TheseException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TheseException("please find something");
        }

        String keyword = parts[1];

        TaskList list = these.getTaskList();
        TaskList found = new TaskList();

        for (int i = 1; i < these.getTaskList().getId(); i++) {
            Task task = list.getTask(i);
            if (task.getName().contains(keyword)) {
                found.addTask(task);
            }
        }

        if (found.isEmpty()) {
            these.getUi().showMessage("No matching tasks found. Sorry!");
            return true;
        }

        String msg = "Here are the matching tasks in your list:";
        these.getUi().showMessage(msg);
        for (int i = 1; i < found.getId(); i++) {
            Task task = found.getTask(i);
            these.getUi().showMessage(task.getId() + "." + task);
        }

        return true;
    }
}
