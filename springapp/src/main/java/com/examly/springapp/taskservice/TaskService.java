package com.examly.springapp.taskservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.employeemodel.EmployeeModel;
import com.examly.springapp.taskmodel.TaskModel;
import com.examly.springapp.taskrepo.TaskRepo;
@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepository;

    public List<TaskModel> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<TaskModel> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public TaskModel createTask(TaskModel task) {
        if (task.getTaskname() == null || task.getTaskname().trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be null or empty");
        }
            if (task.getEmployees() != null) {
            for (EmployeeModel employee : task.getEmployees()) {
                employee.setTask(task); // Ensure bidirectional link
            }
        } 
    
        return taskRepository.save(task);
    }
    

    public TaskModel updateTask(Long id, TaskModel updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTaskname(updatedTask.getTaskname());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public List<TaskModel> getTaskName(String name) {
        return taskRepository.findByName(name);
    }
      public List<TaskModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return taskRepository.findAll(sort);
    }

    public List<TaskModel> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return taskRepository.findAll(page).getContent();
    }

    public List<TaskModel> pagesort(int pageSize, int pageNumber, String field) {
        return taskRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }
    public void addTask(String taskname) {
        taskRepository.insertTask(taskname);
    }


}
