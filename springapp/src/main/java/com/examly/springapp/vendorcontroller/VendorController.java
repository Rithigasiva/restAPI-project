package com.examly.springapp.vendorcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.vendormodel.VendorModel;
import com.examly.springapp.vendorservice.VendorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")

public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public List<VendorModel> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorModel> getVendorById(@PathVariable Long id) {
        Optional<VendorModel> vendor = vendorService.getVendorById(id);
        return vendor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public VendorModel createVendor(@RequestBody VendorModel vendor) {
        return vendorService.saveVendor(vendor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorModel> updateVendor(@PathVariable Long id, @RequestBody VendorModel updatedVendor) {
        return vendorService.getVendorById(id)
                .map(existingVendor -> {
                    existingVendor.setVendorName(updatedVendor.getVendorName());
                    existingVendor.setEventName(updatedVendor.getEventName());
                    existingVendor.setStallNumber(updatedVendor.getStallNumber());
                    existingVendor.setMenu(updatedVendor.getMenu());
                    return ResponseEntity.ok(vendorService.saveVendor(existingVendor));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        if (vendorService.getVendorById(id).isPresent()) {
            vendorService.deleteVendor(id);
            return ResponseEntity.ok("Deleted successfully!");

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/sortBy/{field}")
    public List<VendorModel> getSorted(@PathVariable String field) {
        return vendorService.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<VendorModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return vendorService.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<VendorModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return vendorService.pagesort(pagesize, offset, field);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<VendorModel>> getVendorByName(@PathVariable String name) {
        List<VendorModel> vendors = vendorService.getVendorByName(name);
        if (vendors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendors);
    }
    @PostMapping("/add")
    public String addVendor(
            @RequestParam String vendorName,
            @RequestParam String eventName,
            @RequestParam String stallNumber,
            @RequestParam String menu) {
        
        vendorService.addVendor(vendorName, eventName, stallNumber, menu);
        return "Vendor added successfully!";
    }
}


       
    
    


