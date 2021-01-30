package com.grandstand;

import java.util.Vector;

public class TaskService {

    private Vector<Task> taskList;
    private int idCounter;

    public TaskService() {
        this.taskList = new Vector<Task>();
        this.idCounter = 1;
    }

    public void addTask(String taskName, String taskDescription)
        throws IllegalArgumentException, NullPointerException {

        String taskId = String.format("%010d", this.idCounter);

        Task newTask = new Task(taskId);
        newTask.setName(taskName);
        newTask.setDescription(taskDescription);

        this.taskList.add(newTask);
        this.idCounter++;

    }

    public Task getTaskById(String taskId) {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                return this.taskList.elementAt(i);
            }
        }
        throw new NullPointerException("Task ID not found");
    }

    public void deleteTask(String taskId) {
        for (int i = 0; i < this.taskList.size(); i++) {
            String thisId = this.taskList.elementAt(i).getId();
            if (thisId.equals(taskId)) {
                this.taskList.remove(i);
                return;
            }
        }
        throw new NullPointerException("Task ID not found");
    }

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
