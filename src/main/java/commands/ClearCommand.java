package commands;

import app.These;
import exceptions.TheseException;
import tasks.Task;
import ui.Ui;

public class ClearCommand implements Command {
    private These these;

    public ClearCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {
        these.getTaskList().clear();
        these.getStorage().updateTasks(these.getTaskList());
        these.getUi().showMessage("All tasks cleared!");
        return true;
    }
}
