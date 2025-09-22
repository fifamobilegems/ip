package app;

//CHECKSTYLE.OFF: AvoidStarImport
import commands.*;
//CHECKSTYLE.ON: AvoidStarImport
import exceptions.TheseException;
import storage.Storage;
import TaskList.TaskList;
import ui.Ui;
import parser.Parser;

/**
 * The main entry point of the Duke application (Renamed to 'These').
 *
 * The These class coordinates interactions between the user interface,
 * task list, storage, and parser. It is responsible for initializing the system,
 * running the main command loop, and persisting changes to storage.
 *
 */
public class These {
    private Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Construct a new These instance
     *
     * The constructor creates a new {@link Ui}, {@link Storage}
     * and {@link TaskList} instance. It tries to load in existing tasks from the storage
     * and displays an error and continues with an empty task list
     */
    public These() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();

        try {
            int taskId = storage.loadTasks(taskList);
            this.taskList.setId(taskId);
        } catch (Exception e) {
            ui.showError("Starting with empty list, " + e.getMessage());
        }
    }

    /**
     * Return the task list used by this These instance
     *
     * @return {@link TaskList} containing These's current tasks
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Return the Ui used by this These instance
     *
     * @return {@link Ui} responsible for user interface
     */
    public Ui getUi() { return this.ui; }

    /**
     * Return the storage used by this These instance
     *
     * @return {@link Storage} instance responsible for updating and loading tasks
     */
    public Storage getStorage() { return this.storage; }

    /**
     * Runs the main loop of the These application
     *
     * Begins with intro message, and sets isExit boolean to false. While each command
     * returns true, isExit will remain false until we receive an {@link ExitCommand}
     * which returns false, in that case isExit will be true, and we exit the loop.
     */
    public void run() {
        ui.intro();
        boolean isExit = false;

        while (!isExit) {
            String next = ui.readNext();
            try {
                Command cmd = Parser.parse(next, this);
                isExit = !cmd.run(next);
                storage.updateTasks(taskList);
            } catch (TheseException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                // Catch any unexpected exceptions
                ui.showError("Unexpected error: " + e.getClass().getSimpleName());
            }
        }
    }

    /**
     * Process a single command and return the response for GUI
     *
     * @param input The user input command
     * @return String response to be displayed in GUI
     */
    /**
     * Process a single command and return the response for GUI
     * Uses a temporary UI to capture output from existing commands
     *
     * @param input The user input command
     * @return String response to be displayed in GUI
     */
    public String getResponse(String input) {
        try {
            StringBuilder output = new StringBuilder();
            Ui originalUi = this.ui;

            // Replace current UI with new temp UI
            this.ui = new Ui() {
                @Override
                public void showMessage(String message) {
                    output.append(message).append("\n");
                }

                @Override
                public void showError(String error) {
                    output.append("Error: ").append(error).append("\n");
                }

                @Override
                public void intro() {
                    // Do nothing for GUI
                }

                @Override
                public String readNext() {
                    return ""; // Not used in GUI mode
                }
            };

            Command cmd = Parser.parse(input, this);
            boolean shouldExit = !cmd.run(input);
            storage.updateTasks(taskList);

            // Restore original UI
            this.ui = originalUi;

            // outro
            if (shouldExit) {
                return "Goodbye! Hope to see you again soon!";
            }

            String result = output.toString().trim();
            return result.isEmpty() ? "Command executed successfully" : result;

        } catch (TheseException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new These().run();
    }
}
