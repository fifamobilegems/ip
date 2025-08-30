package commands;

import exceptions.TheseException;
import tasks.Task;
import tasks.Todo;
import app.These;

public class TodoCommand implements Command {
    private These these;

    public TodoCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new TheseException("your todo has nothing");
        }

        int task_id = these.getTask_id();

        Todo todo = new Todo(parts[1], false, these.getTask_id());
        these.addTask(todo);

        String msg = "Got it. I've added this task:\n"
                + todo
                + "\nNow you have " + task_id + " tasks in the list.";
        System.out.println(msg);

        return true;
    }
}
