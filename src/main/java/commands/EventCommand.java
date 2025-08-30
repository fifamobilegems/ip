package commands;

import exceptions.TheseException;
import tasks.Event;
import tasks.Task;
import app.These;

public class EventCommand implements Command {
    private These these;

    public EventCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {

        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new TheseException("your event has nothing");
        }

        String[] fromPart = parts[1].split("/from");
        if (fromPart.length < 2) {
            throw new TheseException("your event needs to have /from field");
        }

        String[] toPart = fromPart[1].split("/to");
        if (toPart.length < 2) {
            throw new TheseException("your event needs to have /to field");
        }

        int task_id = these.getTask_id();

        Event event = new Event(fromPart[0], false, task_id, toPart[0], toPart[1]);
        these.addTask(event);

        String msg = "Got it. I've added this task:\n"
                + event
                + "\nNow you have " + task_id + " tasks in the list.";
        System.out.println(msg);

        return true;
    }
}
