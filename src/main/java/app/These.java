package app;

import exceptions.TheseException;
import tasks.*;
import commands.*;

import java.util.Objects;
import TaskList.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;


public class These {
    private final Ui ui;
    private final TaskList task_list;
    private final Storage storage;

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

    public TaskList getTaskList() {
        return task_list;
    }

    public Ui getUi() { return this.ui; }

    public Storage getStorage() { return this.storage; }

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
