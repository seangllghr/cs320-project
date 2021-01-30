package com.grandstand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        assertDoesNotThrow(() -> task = new Task("1234567890"));
    }

    @Test
    public void testNewTask_bad() {
        assertThrows(IllegalArgumentException.class, () -> {
                new Task("This very long ID is clearly bad.");
            }, "Invalid ID string");
    }

    @Test
    public void testGetId() {
        assertEquals("1234567890", task.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("", task.getName());
    }

    @Test
    public void testSetName_simple() {
        task.setName("This is a task");
        assertEquals("This is a task", task.getName());
    }

    @Test
    public void testSetName_longest() {
        task.setName("This is a long task");
    }

    @Test
    public void testSetName_empty() {
        task.setName("Placeholder");
        task.setName("");
        assertEquals("", task.getName());
    }

    @Test
    public void testSetName_unicode() {
        task.setName("Esta é uma tarefa"); // Português, pra nossos amigos brasileiros
        assertEquals("Esta é uma tarefa", task.getName());
        task.setName("یہ کام کرنا ہے۔"); // And some Urdu, courtesy of my buddy Wiqas
        assertEquals("یہ کام کرنا ہے۔", task.getName());
    }

    @Test
    public void testSetName_overlength() {
        assertThrows(IllegalArgumentException.class, () -> {
                task.setName("This is a very long task");
            }, "Task name must be less than 20 characters");
    }

    @Test
    public void testSetName_null() {
        assertThrows(NullPointerException.class, () -> {
                task.setName(null);
            }, "Task name cannot be null");
    }

    @Test
    public void testGetDescription() {
        assertEquals("", task.getDescription());
    }

    @Test
    public void testSetDescription() {
        String newDescription = "This is a longer description of the task.";
		task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void testSetDescription_maximum() {
        String newDescription = "A description which fits the specified max length.";
		task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void testSetDescription_empty() {
		task.setDescription("A placeholder description.");
		task.setDescription("");
        assertEquals("", task.getDescription());
    }

    @Test
    public void testSetDescription_unicode() {
        // Some Latin diacritics.
        String newDescription = "Esta descripción tiene diacríticos.";
		task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());
        // Google tells me that this next one says "This description contains
        // non-Latin characters." in Simplified Chinese.
        newDescription = "此说明使用非拉丁字符。";
		task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());
    }

}
