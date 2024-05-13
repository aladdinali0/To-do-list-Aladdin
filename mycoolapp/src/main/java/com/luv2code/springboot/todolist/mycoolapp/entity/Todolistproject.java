package com.luv2code.springboot.todolist.mycoolapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="todolistproject")
public class Todolistproject {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="task_name")
    private String taskname;

    @Column(name="description")
    private String description;

    @Column(name="completed")
    private boolean completed;

    // define constructors
    public Todolistproject(){
    }

    public Todolistproject(String taskname, String description, Boolean completed){
        this.taskname = taskname;
        this.description = description;
        this.completed = completed;

    }



    // define getters/setters
    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname){
        this.taskname = taskname;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getCompleted(){
        return completed;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }




    // define toString() method


    @Override
    public String toString() {
        return "Todolistproject{" +
                "id=" + id +
                ", taskname='" + taskname + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}