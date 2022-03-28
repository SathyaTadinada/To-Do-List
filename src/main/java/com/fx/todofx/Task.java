package com.fx.todofx;

/**
 * A class that defines a "task" for a To Do List.
 */
public class Task {
    private String name;
    private String dueDate;
    private String notes;

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
     * A parameterized constructor for a task.
     * @param name Name of the task.
     * @param dueDate Due date of the task.
     * @param notes Notes of the task.
     */
    public Task(String name, String dueDate, String notes) {
        this.name = name;
        this.dueDate = dueDate;
        this.notes = notes;
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
     * Sets the name of the task.
     * @param name Name of the task.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the due date of the task.
     * @return Due date of the task.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the task.
     * @param dueDate Due date of the task.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the notes of the task.
     * @return Notes of the task
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes of the task.
     * @param notes Notes of the task.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Overridden toString method, displays name of task and its due date.
     * @return Name of task and due date.
     */
    public String toString() {
        return name;
    }

    public int hashCode() {
        dueDate = dueDate.replaceAll("-", "");
        return Integer.parseInt(dueDate);
    }
}