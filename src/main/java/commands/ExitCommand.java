package commands;

import app.These;
import exceptions.TheseException;
import ui.Ui;

public class ExitCommand implements Command {
    private These these;

    public ExitCommand(These these) {
        this.these = these;
    }

    @Override
    public boolean run(String input) throws TheseException {
        Ui ui = these.getUi();
        ui.outro();
        return false;
    }
}
