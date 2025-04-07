package com.examly.springapp.employeeservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.employeemodel.EmployeeModel;
import com.examly.springapp.employeerepo.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo ar;
   
    public List<EmployeeModel> getAllEmployees() {
        return ar.findAll();
    }
    
    public void deleteEmployee(Long id) {
        ar.deleteById(id);
    }
    public List<EmployeeModel> getEmployeesByName(String name) {
        return ar.findByName(name);
    }
      public List<EmployeeModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return ar.findAll(sort);
    }

    public List<EmployeeModel> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return ar.findAll(page).getContent();
    }

    public List<EmployeeModel> pagesort(int pageSize, int pageNumber, String field) {
        return ar.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }
    public EmployeeModel saveEmployee(EmployeeModel employee) {
        return ar.save(employee);
    }
    
    public Optional<EmployeeModel> getEmployeeById(Long id) {
        return ar.findById(id);
    }
    public void saveEmployee(String name, String designation, String event) {
        ar.insertEmployee(name, designation, event);
    }

    
}
