package com.examly.springapp.employeecontroller;

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

import com.examly.springapp.employeemodel.EmployeeModel;
import com.examly.springapp.employeeservice.EmployeeService;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService as;


    @GetMapping
    public List<EmployeeModel> getAllEmployees() {
        return as.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeModel> employee = as.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
        return as.saveEmployee(employee);
    }



    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel employee) {
        Optional<EmployeeModel> existingEmployee = as.getEmployeeById(id);
        
        if (existingEmployee.isPresent()) {
            employee.setId(id);
            return ResponseEntity.ok(as.saveEmployee(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
      @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam String name, 
                                              @RequestParam String designation, 
                                              @RequestParam String event) {
        as.saveEmployee(name, designation, event);
        return ResponseEntity.ok("Employee added successfully!");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Optional<EmployeeModel> existingEmployee = as.getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            as.deleteEmployee(id);
            return ResponseEntity.ok("Deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeModel>> getEmployeesByName(@PathVariable String name) {
        List<EmployeeModel> employees = as.getEmployeesByName(name);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/sortBy/{field}")
    public List<EmployeeModel> getSorted(@PathVariable String field) {
        return as.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<EmployeeModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return as.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<EmployeeModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return as.pagesort(pagesize, offset,field);
    }

    

}
