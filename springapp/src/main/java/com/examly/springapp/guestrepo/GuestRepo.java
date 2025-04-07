package com.examly.springapp.guestrepo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.guestmodel.GuestModel;
@Repository
public interface GuestRepo extends JpaRepository<GuestModel,Long> {
   @Query("SELECT a FROM GuestModel a WHERE a.name = :name")
    List<GuestModel> findByName(@Param("name") String name);
    // List<attendeeModel> findByName(String name);     

    // Page<attendeeModel> findAll(Pageable pageable);    
    @Query("SELECT a FROM GuestModel a")
    List<GuestModel> getAllGuests();

    @Query("SELECT a FROM GuestModel a WHERE a.id = :id")
    GuestModel getGuestById(@Param("id") Long id);
     
    @Modifying
    @Transactional
    @Query(value="INSERT INTO guests (name) VALUES (:name)", nativeQuery = true)
    void insertGuest(@Param("name") String name);


}
