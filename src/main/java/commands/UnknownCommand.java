package commands;

import app.These;
import exceptions.TheseException;

/**
 * Represents an unknown command, executed when the parser does not recognise
 * the command being given in the user input. It does not touch the state of
 * {@link app.These} and simply shows an error message and returns true.
 */
public class UnknownCommand implements Command {
    private These these;

    /**
     * Create a new UnknownCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public UnknownCommand(These these) {
        this.these = these;
    }

    /**
     * Executes unknown command that does nothing except print an error message.
     *
     * @param input some command i dont know
     * @return true all the time
     * @throws TheseException this command is the exception
     */
    @Override
    public boolean run(String input) throws TheseException {
        these.getUi().showMessage("This command isn't recognised please command better");
        return true;
    }
}
