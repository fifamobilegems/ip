package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;
import TaskList.TaskList;

public class ListCommand implements Command {
    private These these;

    public ListCommand(These these) {
        this.these = these;
    }

    public boolean run(String input) throws TheseException {

        TaskList task_list = these.getTaskList();
        int task_id = these.getTaskList().getId();

        String msg = "Here are the tasks in your list:";
        these.getUi().showMessage(msg);
        for (int i = 1; i < task_id; i++) {
            Task task = task_list.getTask(i);
            these.getUi().showMessage(task.getId() + "." + task);
        }
        return true;
    }
}
