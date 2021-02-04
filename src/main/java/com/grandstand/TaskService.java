package com.grandstand;

import java.util.Vector;

/**
 * Manages the in-memory task list
 */
public class TaskService {

    private Vector<Task> taskList;
    private int idCounter;

    public TaskService() {
        this.taskList = new Vector<Task>();
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
     * Loops through the task list looking for the target task
     *
     * @param taskId the ID string of the target task
     * @return the {@code Task} object matching {@code taskId}
     * @throws NullPointerException if {@code taskId} is not found
     */
    public Task getTaskById(String taskId) throws NullPointerException {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                return this.taskList.elementAt(i);
            }
        }
        throw new NullPointerException("Task ID not found");
    }

    /**
     * Searches through the task list for a matching task and removes it
     *
     * @param taskId the ID string of the task to remove
     * @throws NullPointerException if no task matches the target ID
     */
    public void deleteTask(String taskId) throws NullPointerException {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                this.taskList.remove(i);
                return;
            }
        }
        throw new NullPointerException("Task ID not found");
    }

    /**
     * Update the specified field of the target task with the specified value
     *
     * @param taskId the ID string of the task to modify
     * @param field a field to update, from the {@code Task.UpdateableField} enum
     * @param value the new value of the field
     * @throws IllegalArgumentException if the update value is invalid
     * @throws NullPointerException if the task cannot be found
     */
    public void updateTask(String taskId, Task.UpdateableField field, String value)
        throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                switch (field) {
                case NAME:
                    this.taskList.elementAt(i).setName(value);
                    return;
                case DESCRIPTION:
                    this.taskList.elementAt(i).setDescription(value);
                    return;
                }
            }
        }
        throw new NullPointerException("Task ID not found");
    }

}
