package com.mad1.tasklistdbik.data;


public class Task {
    private int taskId;
    private int listId;
    private String name;
    private String notes;
    private String completed;
    private String hidden;

    public static final String TRUE = "1";
    public static final String FALSE = "0";

    public Task() {
        name = "";
        notes = "";
        completed = FALSE;
        hidden = FALSE;
    }


    public Task(int listId, String name, String notes,
                String completed, String hidden) {
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completed = completed;
        this.hidden = hidden;
    }
    public Task(int taskId, int listId, String name,
                String notes,
                String completed, String hidden) {
        this.taskId = taskId;
        this.listId = listId;
        this.name = name;
        this.notes = notes;
        this.completed = completed;
        this.hidden = hidden;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }
    public long getCompletedMillis() {
        return Long.valueOf(completed);
    }

    @Override
    public String   toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", completed='" + completed + '\'' +
                ", hidden='" + hidden + '\'' +
                '}';
    }
}
