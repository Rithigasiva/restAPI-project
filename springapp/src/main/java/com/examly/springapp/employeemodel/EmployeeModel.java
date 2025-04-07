package com.examly.springapp.employeemodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.examly.springapp.taskmodel.TaskModel;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "employees")

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeName;
    private String designation;
    
    private String eventname;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private TaskModel task;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getEventname() {
        return eventname;
    }
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
    public TaskModel getTask() {
        return task;
    }
    public void setTask(TaskModel task) {
        this.task = task;
    }
     
}
