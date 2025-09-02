import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import TaskList.TaskList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTaskIncreaseId() {
        Task t1 = new Task("read book", false, taskList.getId());
        taskList.addTask(t1);

        assertEquals(2, taskList.getId(), "Task ID should increment after adding");
        assertEquals("read book", taskList.getTask(1).getName());
    }

    @Test
    void testDeleteTaskRemovesAndShifts() {
        Task t1 = new Task("task1", false, taskList.getId());
        Task t2 = new Task("task2", false, taskList.getId() + 1);
        taskList.addTask(t1);
        taskList.addTask(t2);

        Task deleted = taskList.deleteTask(1);

        assertEquals("task1", deleted.getName(), "Deleted task should be task1");
        assertEquals("task2", taskList.getTask(1).getName(), "task2 should shift down");
        assertEquals(2, taskList.getId(), "Task ID should decrement");
    }

    @Test
    void testClearResetsList() {
        taskList.addTask(new Task("read book", false, 1));
        taskList.clear();

        assertEquals(1, taskList.getId(), "ID should reset to 1 after clear");
        assertNull(taskList.getTask(1), "Tasks should be cleared");
    }
}
