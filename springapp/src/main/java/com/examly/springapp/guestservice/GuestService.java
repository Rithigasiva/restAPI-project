package com.examly.springapp.guestservice;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.eventmodel.EventModel;
import com.examly.springapp.feedbackmodel.FeedbackModel;
import com.examly.springapp.guestmodel.GuestModel;
import com.examly.springapp.guestrepo.GuestRepo;
import com.examly.springapp.taskmodel.TaskModel;

@Service
public class GuestService {

    private final GuestRepo ar;

    public GuestService(GuestRepo ar) {
        this.ar = ar;
    }

    public List<GuestModel> getAllGuests() {
        return ar.findAll();
    }

    public Optional<GuestModel> getGuestById(Long id) {
        return ar.findById(id);
    }

    // public GuestModel saveGuest(GuestModel attendee) {
    //     return ar.save(attendee);
    // }

      public GuestModel saveGuest(GuestModel guest)
        {
            for (FeedbackModel feedback : guest.getFeedback())
            {
                 feedback.setGuest(guest); // Ensure bidirectional link
            }
            return ar.save(guest);
        }

    public void deleteGuest(Long id) {
        ar.deleteById(id);
    }

    public GuestModel updateGuest(Long id, GuestModel updatedGuest) {
        return ar.findById(id)
                .map(guest -> {
                    guest.setName(updatedGuest.getName()); // Update name field
                    return ar.save(guest);
                })
                .orElseThrow(() -> new RuntimeException("Attendee not found with id " + id));
    }

    public List<GuestModel> getGuestsByName(String name) {
        return ar.findByName(name);
    }

    public List<GuestModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return ar.findAll(sort);
    }

    public List<GuestModel> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return ar.findAll(page).getContent();
    }

    public List<GuestModel> pagesort(int pageSize, int pageNumber, String field) {
        return ar.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }
    public void addGuest(String name) {
        ar.insertGuest(name);
    }
      

}
