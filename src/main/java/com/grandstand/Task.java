package com.grandstand;

/**
 * Represents a single to-do list item in memory
 */
public class Task {

    private final String ID;
    private String name;
    private String description;

    public static enum UpdateableField {
        NAME, DESCRIPTION
    }

    /**
     * Create a new Task
     *
     * @param taskId a string of no more than 10 alphanumeric characters
     * @throws IllegalArgumentException if the ID string is invalid
     */
    public Task(String taskId) throws IllegalArgumentException {
        if (taskId.matches("[0-9A-Za-z]+")) {
            this.ID = taskId;
        } else {
            throw new IllegalArgumentException("Invalid ID string");
        }
        this.name = "";
        this.description = "";
    }

    public String getId() {
        return ID;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Change the task name
     *
     * @param newName the new task name string of 20 chars or less
     * @throws IllegalArgumentException for an invalid name string
     * @throws NullPointerException if {@code null} is passed
     */
    public void setName(String newName)
        throws IllegalArgumentException, NullPointerException {
        if (newName == null) {
            throw new NullPointerException("Task name cannot be null");
        }
        if (newName.length() <= 20) {
            this.name = newName;
        } else {
            String errorMessage = "Task name must not exceed 20 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Change the task description
     *
     * @param newDescription the new description string of 50 chars or less
     * @throws IllegalArgumentException for an invalid description string
     * @throws NullPointerException if {@code null} is passed
     */
    public void setDescription(String newDescription)
        throws IllegalArgumentException, NullPointerException {
        if (newDescription == null) {
            throw new NullPointerException("Task description cannot be null");
        }
        if (newDescription.length() <= 50) {
            this.description = newDescription;
        } else {
            String errorMessage = "Task description must not exceed 50 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
