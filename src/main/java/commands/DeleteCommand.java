package commands;

import tasks.Task;
import exceptions.TheseException;
import app.These;

public class DeleteCommand implements Command {
    private These these;

    public DeleteCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {
        //exception
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new TheseException("you're deleting nothing");
        }

        int del_index = Integer.parseInt(parts[1]);
        Task t = these.deleteTask(del_index);

        String msg = "Noted. I've removed this task:\n" + t.toString()
                + "\nNow you have " + (these.getTask_id()-1) + " tasks in the list";
        System.out.println(msg);
        return true;
    }
}
