package com.fx.todofx;

/**
 * A class that defines a "task" for a To Do List.
 */
public class Task {
    private String name;
    private String dueDate;

    /**
     * A parameterized constructor for a task.
     * @param name Name of the task.
     * @param dueDate Due date of the task.
     */
    public Task(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    /**
     * Default constructor.
     */
    public Task() {
        this.name = "Untitled task";
        this.dueDate = "2022-03-20";
    }

    /**
     * Gets the name of the task.
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the due date of the task.
     * @return Due date of the task.
     */
    public String getDueDate() {
        return dueDate;
    }
}