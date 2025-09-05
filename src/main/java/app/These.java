package app;

import exceptions.TheseException;
import tasks.*;
import commands.*;

import java.util.Objects;
import TaskList.TaskList;
import ui.Ui;
import storage.Storage;
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
    private final Ui ui;
    private final TaskList task_list;
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
        this.task_list = new TaskList();

        try {
            int task_id = storage.loadTasks(task_list);
            this.task_list.setId(task_id);
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
        return task_list;
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
     * which returns false, in that case isExit will be true and we exit the loop.
     */
    public void run() {
        ui.intro();
        boolean isExit = false;

        while (!isExit) {
            String next = ui.readNext();
            try {
                Command cmd = Parser.parse(next, this);
                isExit = !cmd.run(next);
                storage.updateTasks(task_list);
            } catch (TheseException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new These().run();
    }
}
