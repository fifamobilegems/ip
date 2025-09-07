package TaskList;

import tasks.Task;

public class TaskList {
    private Task[] task_list;
    private int task_id = 1;

    public TaskList() {
        this.task_list = new Task[100];
    }

    public void setId(int new_id) {
        this.task_id = new_id;
    }

    public Task[] getTask_list() {
        return task_list;
    }

    public boolean isEmpty() {
        return task_id == 1;
    }

    public void clear() {
        this.task_list = new Task[100];
        this.task_id = 1;
    }

    public Task getTask(int task_id) {
        return task_list[task_id];
    }

    public void addTask(Task t) {
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
