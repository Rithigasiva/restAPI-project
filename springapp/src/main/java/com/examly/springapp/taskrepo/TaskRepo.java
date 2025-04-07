package com.examly.springapp.taskrepo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.taskmodel.TaskModel;

public interface TaskRepo extends JpaRepository<TaskModel,Long>{
    @Query("SELECT b FROM TaskModel b WHERE b.taskname = :name")
    List<TaskModel> findByName(@Param("name") String name);

    @Query("SELECT a FROM TaskModel a")
    List<TaskModel> getAllTasks();

    @Query("SELECT a FROM TaskModel a WHERE a.id = :id")
    TaskModel getTaskById(@Param("id") Long id);

      @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks (taskname) VALUES (:taskname)", nativeQuery = true)
    void insertTask(@Param("taskname") String taskname);


}