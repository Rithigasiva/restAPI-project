package com.examly.springapp.eventcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.eventmodel.EventModel;
import com.examly.springapp.eventservice.EventService;
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService os;
     public EventController(EventService os) {
        this.os = os;
    }

    @GetMapping
    public List<EventModel> getAllEvents() {
        return os.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventModel> getEventById(@PathVariable Long id) {
        Optional<EventModel> event = os.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EventModel createEvent(@RequestBody EventModel event) {
        return os.saveEvent(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        os.deleteEvent(id);
        return ResponseEntity.ok("Deleted successfully!");

    }
       @GetMapping("/name/{name}")
    public ResponseEntity<List<EventModel>> getEventName(@PathVariable String name) {
        List<EventModel> events = os.getEventName(name);
        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }
    @GetMapping("/sortBy/{field}")
    public List<EventModel> getSorted(@PathVariable String field) {
        return os.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<EventModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return os.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<EventModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return os.pagesort(pagesize, offset,field);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestParam String eventName) {
        os.addEvent(eventName);
        return ResponseEntity.ok("Event added successfully!");
    }

   
}
