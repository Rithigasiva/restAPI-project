package com.examly.springapp.vendorservice;
import com.examly.springapp.vendormodel.VendorModel;
import com.examly.springapp.vendorrepo.VendorRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepository;

    public List<VendorModel> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<VendorModel> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    public VendorModel saveVendor(VendorModel vendor) {
        return vendorRepository.save(vendor);
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
    public List<VendorModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return vendorRepository.findAll(sort);
    }

    public List<VendorModel> page(int pageSize, int pageNumber) {
        PageRequest page = PageRequest.of(pageNumber, pageSize);
        return vendorRepository.findAll(page).getContent();
    }

    public List<VendorModel> pagesort(int pageSize, int pageNumber, String field) {
        return vendorRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
            }
            public List<VendorModel> getVendorByName(String name) {
        return vendorRepository.findByName(name);
    }

    public void addVendor(String vendorName, String eventName, String stallNumber, String menu) {
        vendorRepository.insertVendor(vendorName, eventName, stallNumber, menu);
    }
}

   
