package com.examly.springapp.eventservice;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.artistmodel.ArtistModel;
import com.examly.springapp.eventmodel.EventModel;
import com.examly.springapp.eventrepo.EventRepo;
import com.examly.springapp.guestmodel.GuestModel;
import com.examly.springapp.taskmodel.TaskModel;

@Service
public class EventService 
{
    
        private final EventRepo or;
    
        public EventService(EventRepo or) {
            this.or = or;
        }
    
        public List<EventModel> getAllEvents() {
            return or.findAll();
        }
    
        public Optional<EventModel> getEventById(Long id) {
            return or.findById(id);
        }
    
        // public EventModel saveEvent(EventModel event) {
        //     return or.save(event);
        // }

        public EventModel saveEvent(EventModel event)
        {
            for (TaskModel task : event.getTask())
            {
            task.setEvent(event); // Ensure bidirectional link
            }
            
            for (GuestModel guest : event.getGuest()) {
                guest.setEvent(event); // Ensure bidirectional link for guests
            }
          
            return or.save(event);
        }

        public void deleteEvent(Long id) {
            or.deleteById(id);
        }
        public List<EventModel> getEventName(String name) {
        return or.findByName(name);
        }
      public List<EventModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return or.findAll(sort);
    }

    public List<EventModel> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return or.findAll(page).getContent();
    }

    public List<EventModel> pagesort(int pageSize, int pageNumber, String field) {
        return or.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }
    public void addEvent(String eventName) {
        or.insertEvent(eventName);
    }
  

}
    


