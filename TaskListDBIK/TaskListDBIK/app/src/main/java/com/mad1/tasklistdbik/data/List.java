package com.mad1.tasklistdbik.data;

public class List {
    private int id;
    private String name;


    public List(){

    }

    public List (String name){
        this.name = name;
    }

    public List(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
