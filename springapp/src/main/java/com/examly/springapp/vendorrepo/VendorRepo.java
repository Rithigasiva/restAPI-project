package com.examly.springapp.vendorrepo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.examly.springapp.vendormodel.VendorModel;

public interface VendorRepo extends JpaRepository<VendorModel,Long> {
    
    @Query("SELECT a FROM VendorModel a WHERE a.vendorName = :name")
    List<VendorModel> findByName(@Param("name") String name);

    @Query("SELECT a FROM VendorModel a")
    List<VendorModel> getAllVendors();

    @Query("SELECT a FROM VendorModel a WHERE a.id = :id")
    VendorModel getVendorById(@Param("id") Long id);

      @Modifying
    @Transactional
    @Query(value = "INSERT INTO vendors (vendor_name, event_name, stall_number, menu) VALUES (:vendorName, :eventName, :stallNumber, :menu)", nativeQuery = true)
    void insertVendor(@Param("vendorName") String vendorName, 
                      @Param("eventName") String eventName, 
                      @Param("stallNumber") String stallNumber, 
                      @Param("menu") String menu);

}
