package commands;

import exceptions.TheseException;
import tasks.Task;
import app.These;

public class ListCommand implements Command {
    private These these;

    public ListCommand(These these) {
        this.these = these;
    }

    public boolean run(String input) throws TheseException {

        Task[] task_list = these.getTask_list();
        int task_id = these.getTask_id();

        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < task_id; i++) {
            Task these = task_list[i];
            System.out.println(these.getId() + "." + these);
        }
        return true;
    }
}
