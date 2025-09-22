package commands;

import app.These;
import exceptions.TheseException;
import ui.Ui;

/**
 * Represents a command that terminates the application
 *
 * When executed, display the outro message through the {@link Ui} component and signals
 * the main loop in {@link These} to stop running.
 */
public class ExitCommand implements Command {
    private final These these;

    /**
     * Create a new ExitCommand associated with a These instance
     *
     * @param these the main application instance that provides access
     * to the task list, UI, and storage
     */
    public ExitCommand(These these) {
        assert these != null : "These must not be null";
        this.these = these;
    }

    /**
     * On execution, display the outro message and return false, causing the loop in these
     * to break and exiting the program.
     *
     * @param input expects the input "bye"
     * @return false, allowing user to exit the program
     * @throws TheseException not thrown in this method, but declared to
     * align with the implementation in the {@link Command} interface
     */
    @Override
    public boolean run(String input) throws TheseException {
        Ui ui = these.getUi();
        ui.outro();
        return false;
    }
}
