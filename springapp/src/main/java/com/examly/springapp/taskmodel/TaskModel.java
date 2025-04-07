package com.examly.springapp.taskmodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.examly.springapp.employeemodel.EmployeeModel;
import com.examly.springapp.eventmodel.EventModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskname;
    
    @OneToMany(mappedBy="task",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EmployeeModel> employees=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="event_id")
    @JsonBackReference
    private EventModel event;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees)
    {
        for(EmployeeModel empl:employees)
        {
            empl.setTask(this);
        }
            this.employees.addAll(employees);
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    
    

}
