package TaskList;

import java.util.Arrays;
import tasks.Task;

public class TaskList {
    private static final int INITIAL_CAPACITY = 100;

    private Task[] task_list;
    private int task_id = 1;

    public TaskList() {
        this.task_list = new Task[INITIAL_CAPACITY];
    }

    public void setId(int new_id) {
        this.task_id = new_id;
    }

    public void clear() {
        Arrays.fill(this.task_list, null);
        this.task_id = 1;
    }

    public Task getTask(int id) {
        if (id < 1 || id >= task_id) return null;
        return task_list[id];
    }

    public void addTask(Task t) {
        if (t == null) throw new IllegalArgumentException("task cannot be null");
        task_list[task_id] = t;
        task_id++;
    }

    public int getId() {
        return this.task_id;
    }

    public Task deleteTask(int del_index) {
        Task t = task_list[del_index];
        for (int i = del_index; i < task_id-1; i++) {
            task_list[i] = task_list[i+1];
            task_list[i].dropId();
        }
        task_id--;
        return t;
    }
}
