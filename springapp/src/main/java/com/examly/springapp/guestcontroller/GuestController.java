package com.examly.springapp.guestcontroller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.guestmodel.GuestModel;
import com.examly.springapp.guestservice.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<GuestModel> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestModel> getGuestById(@PathVariable Long id) {
        Optional<GuestModel> guest = guestService.getGuestById(id);
        return guest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GuestModel createGuest(@RequestBody GuestModel attendee) {
        return guestService.saveGuest(attendee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestModel> updateGuest(@PathVariable Long id, @RequestBody GuestModel updatedAttendee) {
        GuestModel guest = guestService.updateGuest(id, updatedAttendee);
        return ResponseEntity.ok(guest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.ok("Deleted successfully!");

    }
     @GetMapping("/name/{name}")
    public ResponseEntity<List<GuestModel>> getGuestsByName(@PathVariable String name) {
        List<GuestModel> guests = guestService.getGuestsByName(name);
        if (guests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guests);
    }
    @GetMapping("/sortBy/{field}")
    public List<GuestModel> getSorted(@PathVariable String field) {
        return guestService.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<GuestModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return guestService.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<GuestModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return guestService.pagesort(pagesize, offset, field);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addGuest(@RequestParam String name) {
        guestService.addGuest(name);
        return ResponseEntity.ok("Guest added successfully!");
    }
}
