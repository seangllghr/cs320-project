package com.grandstand;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.grandstand.Task.UpdateableField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private TaskService ts;
    private final String TEST_NAME = "This is a task";
    private final String TEST_DESCRIPTION = "Atonishing! Exactly 50 characters of description";

    @BeforeEach
    public void setUp() {
        // Create a new task service and pump it full of nonsense
        ts = new TaskService();
        assertDoesNotThrow(() -> {
                for (int i = 0; i < 10; i++) {
                    ts.addTask(TEST_NAME, TEST_DESCRIPTION);
                }
            });
    }

    @Test
    public void testAddManyTasks() {
        String taskId = "0000000006";
		Task fifthTask = ts.getTaskById(taskId);
        assertEquals(taskId, fifthTask.getId());
    }

    @Test
    public void testAddBadTask() {
        assertThrows(IllegalArgumentException.class, () -> {
                ts.addTask("This is a bad task name because it's long.", TEST_DESCRIPTION);
            }, "Task name must not exceed 20 characters");
        assertThrows(NullPointerException.class, () -> {
                ts.addTask(TEST_NAME, null);
            }, "Task description cannot be null");
    }

    @Test
    public void testGetTaskById() {
        String taskId = "0000000001";
        assertEquals(taskId, ts.getTaskById(taskId).getId());
    }

    @Test
    public void testGetNonexistentTask() {
        String taskId = "0123456789";
        assertThrows(NullPointerException.class, () -> {
                ts.getTaskById(taskId);
            }, "Task ID not found");
    }

    @Test
    public void testDeleteTask_first() {
        String taskId = "0000000001";
        assertEquals(taskId, ts.getTaskById(taskId).getId());
        assertDoesNotThrow(() -> ts.deleteTask(taskId));
        assertThrows(NullPointerException.class,
                     () -> ts.getTaskById(taskId),
                     "Task ID not found");
    }

    @Test
    public void testDeleteTask_middle() {
        String taskId = "0000000004";
        assertEquals(taskId, ts.getTaskById(taskId).getId());
        assertDoesNotThrow(() -> ts.deleteTask(taskId));
        assertThrows(NullPointerException.class,
                     () -> ts.getTaskById(taskId),
                     "Task ID not found");
    }

    @Test
    public void testDeleteTask_last() {
        String taskId = "0000000010";
        assertEquals(taskId, ts.getTaskById(taskId).getId());
        assertDoesNotThrow(() -> ts.deleteTask(taskId));
        assertThrows(NullPointerException.class,
                     () -> ts.getTaskById(taskId),
                     "Task ID not found");
    }

    @Test
    public void testDeleteTask_doesNotRipple() {
        String taskId = "0000000005";
        String taskIdBefore = "0000000004";
        String taskIdAfter = "0000000006";
        assertEquals(taskId, ts.getTaskById(taskId).getId());
        assertDoesNotThrow(() -> ts.deleteTask(taskId));
        assertEquals(taskIdBefore, ts.getTaskById(taskIdBefore).getId());
        assertEquals(taskIdAfter, ts.getTaskById(taskIdAfter).getId());
    }

    @Test
    public void testDeleteBadTask() {
        String taskId = "1234567890";
        assertThrows(NullPointerException.class, () -> {
                ts.deleteTask(taskId);
            }, "Task ID not found");
    }

    @Test
    public void testUpdateTaskName() {
        String taskId = "0000000002";
        String newName = "Task name updated";
        assertEquals(TEST_NAME, ts.getTaskById(taskId).getName());
        ts.updateTask(taskId, UpdateableField.NAME, newName);
        assertEquals(newName, ts.getTaskById(taskId).getName());
    }

    @Test
    public void testUpdateTaskDescription() {
        String taskId = "0000000008";
        String newDesc = "This task description was recently updated.";
        assertEquals(TEST_DESCRIPTION, ts.getTaskById(taskId).getDescription());
        ts.updateTask(taskId, UpdateableField.DESCRIPTION, newDesc);
        assertEquals(newDesc, ts.getTaskById(taskId).getDescription());
    }

    @Test
    public void testUpdateNonexistantTask() {
        String taskId = "0000000042";
        String newName = "Figure out Thursday";
        assertThrows(NullPointerException.class, () -> {
                ts.updateTask(taskId, UpdateableField.NAME, newName);
            }, "Task ID not found");
    }

    @Test
    public void testUpdateTask_badValue() {
        String taskId = "0000000003";
        String newName = "This name is too long";
        assertThrows(IllegalArgumentException.class, () -> {
                ts.updateTask(taskId, UpdateableField.NAME, newName);
            }, "Task name must not exceed 20 characters");
    }

}
