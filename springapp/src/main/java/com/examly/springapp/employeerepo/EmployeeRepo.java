package com.examly.springapp.employeerepo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.employeemodel.EmployeeModel;

public interface EmployeeRepo extends JpaRepository<EmployeeModel,Long> {
    
    
    @Query("SELECT a FROM EmployeeModel a WHERE a.employeeName = :name")
    List<EmployeeModel> findByName(@Param("name") String name);
    
    @Query("SELECT a FROM EmployeeModel a")
    List<EmployeeModel> getAllEmployees();
    
    @Query("SELECT a FROM EmployeeModel a WHERE a.id = :id")
    EmployeeModel getEmployeeById(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employees (employee_name, designation, eventname) VALUES (:name, :designation, :event)", nativeQuery = true)
    void insertEmployee(@Param("name") String name,@Param("designation") String designation,@Param("event") String event);
   




}
