package com.examly.springapp.taskcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.taskmodel.TaskModel;
import com.examly.springapp.taskservice.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
      @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskModel> getAllTask() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<TaskModel> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskModel createTask(@RequestBody TaskModel task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable Long id, @RequestBody TaskModel updatedtask) {
        return taskService.updateTask(id, updatedtask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<TaskModel>> getTaskName(@PathVariable String name) {
        List<TaskModel> task1 = taskService.getTaskName(name);
        if (task1.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(task1);
    }
    @GetMapping("/sortBy/{field}")
    public List<TaskModel> getSorted(@PathVariable String field) {
        return taskService.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<TaskModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return taskService.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<TaskModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return taskService.pagesort(pagesize, offset, field );
    }

    @PostMapping("/add")
    public ResponseEntity<String> createTask(@RequestParam String taskname) {
        taskService.addTask(taskname);
        return ResponseEntity.ok("Task added successfully!");
    }

}
