package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;

import TaskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Storage {
    private File file;
    private static final String TASK_FILE_PATH = "data/tasks.txt";

    public Storage() {
        this.file = new File(TASK_FILE_PATH);
        ensureFileExists();
    }

    public void ensureFileExists() {
        try {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    public int loadTasks(TaskList task_list) {
        int curr_id = 1;
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.trim().isEmpty()) {
                    Task t = parseTask(line, curr_id);
                    task_list.addTask(t);
                    curr_id++;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return curr_id;
    }

    public Task parseTask(String line, int id) {
        String[] parsedLine = line.split("\\|");
        if (parsedLine.length < 3) {
            throw new IllegalArgumentException("Too little fields");
        }

        String type = parsedLine[0];
        boolean isMarked = parsedLine[1].equals("X");
        String title = parsedLine[2];

        switch (type) {
            case "T" -> {
                return new Todo(title, isMarked, id);
            }
            case "D" -> {
                LocalDate by = LocalDate.parse(parsedLine[3]);
                return new Deadline(title, isMarked, id, by);
            }
            case "E" -> {
                LocalDate from = LocalDate.parse(parsedLine[3]);
                LocalDate to = LocalDate.parse(parsedLine[4]);
                return new Event(title, isMarked, id, from, to);
            }
            default -> {
                throw new IllegalArgumentException("Unknown task type in file:" + type);
            }
        }
    }

    public void updateTasks(TaskList task_list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 1; i <= task_list.getId(); i++) {
                Task t = task_list.getTask(i);
                if (t != null) {
                    fw.write(t.toDataFormat() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }
}
