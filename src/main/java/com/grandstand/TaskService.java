package com.grandstand;

import java.util.Vector;

/**
 * Manages the in-memory task list
 */
public class TaskService {

    private final Vector<Task> taskList;
    private int idCounter;

    public TaskService() {
        this.taskList = new Vector<>();
        this.idCounter = 1;
    }

    /**
     * Add a new task to the to-do list
     *
     * @param taskName the name string of the new task
     * @param taskDescription the description string for the new task
     * @throws IllegalArgumentException for invalid name/description
     * @throws NullPointerException if {@code null} is passed for name/description
     */
    public void addTask(String taskName, String taskDescription)
        throws IllegalArgumentException, NullPointerException {

        String taskId = String.format("%010d", this.idCounter);

        Task newTask = new Task(taskId);
        newTask.setName(taskName);
        newTask.setDescription(taskDescription);

        this.taskList.add(newTask);
        this.idCounter++;

    }

    /**
     * Retrieve the index of the task with the specified ID string
     *
     * @param taskId the ID string to match
     * @return the index of the matching task
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code} null is passed or match not found
     */
    private int findTask(String taskId)
        throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                return i;
            }
        }
        throw new NullPointerException("Task ID not found");
    }

    /**
     * Loops through the task list looking for the target task
     *
     * @param taskId the ID string of the target task
     * @return the {@code Task} object matching {@code taskId}
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code null} passed or ID not found
     */
    public Task getTaskById(String taskId) throws NullPointerException {
        int taskIndex = findTask(taskId);
		return this.taskList.elementAt(taskIndex);
    }

    /**
     * Searches through the task list for a matching task and removes it
     *
     * @param taskId the ID string of the task to remove
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code null} passed or task cannot be found
     */
    public void deleteTask(String taskId) throws NullPointerException {
        int taskIndex = findTask(taskId);
		this.taskList.remove(taskIndex);
    }

    /**
     * Update the specified field of the target task with the specified value
     *
     * @param taskId the ID string of the task to modify
     * @param field a field to update, from the {@code Task.UpdateableField} enum
     * @param value the new value of the field
     * @throws IllegalArgumentException if ID string or update value is invalid
     * @throws NullPointerException if {@code null} passed or task cannot be found
     */
    public void updateTask(String taskId, Task.UpdateableField field, String value)
        throws IllegalArgumentException, NullPointerException {
        int taskIndex = findTask(taskId);
        switch (field) {
        case NAME:
            this.taskList.elementAt(taskIndex).setName(value);
            break;
        case DESCRIPTION:
            this.taskList.elementAt(taskIndex).setDescription(value);
            break;
        }
    }

}
