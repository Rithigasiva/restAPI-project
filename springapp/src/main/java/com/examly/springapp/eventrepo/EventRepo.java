package com.examly.springapp.eventrepo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.eventmodel.EventModel;
@Repository
public interface EventRepo extends JpaRepository<EventModel,Long> {
  @Modifying
      @Query("SELECT a FROM EventModel a WHERE a.eventName = :name")
    List<EventModel> findByName(@Param("name") String name);

    @Query("SELECT a FROM EventModel a")
    List<EventModel> getAllEvents();

    @Query("SELECT a FROM EventModel a WHERE a.id = :id")
    EventModel getEventById(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO events (event_name) VALUES (:eventName)", nativeQuery = true)
    void insertEvent(@Param("eventName") String eventName);
     
}
