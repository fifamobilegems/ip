package app;

import exceptions.TheseException;
import tasks.*;
import commands.*;

import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class These {
    private Task[] task_list = new Task[100];
    private int task_id = 1;
    private final Scanner sc = new Scanner(System.in);

    public boolean echo (String input) throws TheseException {
        String intro = "Hello! I'm app.These\n" + "What can I do for you?";
        String outro = "Bye. Hope to see you again soon!";

        String[] parts = input.split(" ", 2);

        if (Objects.equals(input, intro)) {
            System.out.println(intro);
            return true;
        } else if (Objects.equals(input, "bye")) {
            System.out.println(outro);
            return false;
        } else if (Objects.equals(input, "list")) {
            Command cmd = new ListCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("delete")) {
            Command cmd = new DeleteCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("mark")) {
            Command cmd = new MarkCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("unmark")) {
            Command cmd = new UnmarkCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("todo")) {
            Command cmd = new TodoCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("deadline")) {
            Command cmd = new DeadlineCommand(this);
            return cmd.run(input);
        } else if (input.startsWith("event")) {
            Command cmd = new EventCommand(this);
            return cmd.run(input);
        } else {
                throw new TheseException("what are you even saying");
        }
    }

    public Task[] getTask_list() {
        return task_list;
    }

    public int getTask_id() {
        return task_id;
    }

    public void incrementTaskID() {
        task_id++;
    }

    public void decrementTaskId() {
        task_id--;
    }

    public Task getTask(int task_id) {
        return task_list[task_id];
    }

    public void addTask(Task t) {
        task_list[task_id] = t;
        task_id++;
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

    private File getTasksFile() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Created directory: " + dir.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + dir.getAbsolutePath());
            }
        }
        File tasksFile = new File(dir, "tasks.txt");
        if (tasksFile.createNewFile()) {
            System.out.println("Created file: " + tasksFile.getAbsolutePath());
        }
        return tasksFile;
    }

    public void loadTasks() {
        try {
            File tasksFile = getTasksFile();

            Scanner fileScanner = new Scanner(tasksFile);
            int curr_id = 1;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Task t = parseTask(line, curr_id);
                    addTask(t);
                    curr_id++;
                }
            }
            fileScanner.close();

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void updateTasks() {
        try {
            File tasksFile = getTasksFile();
            FileWriter fw = new FileWriter(tasksFile);

            for (int i = 1; i < task_id; i++) {
                Task t = getTask(i);
                if (t != null) {
                    fw.write(t.toDataFormat() + "\n");
                }
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }


    }

    public Task parseTask(String line, int id) {
        String[] parts = line.split("\\|");

        String type = parts[0];
        boolean isMarked = parts[1].equals("X");

        switch (type) {
            case "T" -> {
                return new Todo(parts[2], isMarked, id);
            }
            case "D" -> {
                return new Deadline(parts[2], isMarked, id, parts[3]);
            }
            case "E" -> {
                return new Event(parts[2], isMarked, id, parts[3], parts[4]);
            }
            default -> {
                throw new IllegalArgumentException("Unknown task type in file:" + type);
            }
        }
    }

    public void run() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello! I'm app.These\n" + "What can I do for you?";

        loadTasks();

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);


        while (true) {
            String next = sc.nextLine();
            try {
                boolean exit = echo(next);
                if (!exit) break;
            } catch (TheseException e) {
                System.out.println("woah " + e.getMessage());
            }
            updateTasks();
        }
    }

    public static void main(String[] args) {
        new These().run();
    }
}
