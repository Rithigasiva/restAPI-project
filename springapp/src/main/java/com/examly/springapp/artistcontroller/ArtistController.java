package com.examly.springapp.artistcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.artistmodel.ArtistModel;
import com.examly.springapp.artistservice.ArtistService;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<ArtistModel> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistModel> getArtistById(@PathVariable Long id) {
        Optional<ArtistModel> artist = artistService.getArtistById(id);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ArtistModel createArtist(@RequestBody ArtistModel artist) {
        return artistService.saveArtist(artist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistModel> updateArtist(@PathVariable Long id, @RequestBody ArtistModel updatedArtist) {
        return artistService.getArtistById(id)
                .map(existingArtist -> {
                    existingArtist.setName(updatedArtist.getName());
                    existingArtist.setBio(updatedArtist.getBio());
                    existingArtist.setGenre(updatedArtist.getGenre());
                    // existingArtist.setRating(updatedArtist.getRating());
                    // existingArtist.setEvents(updatedArtist.getEvents());
                    return ResponseEntity.ok(artistService.saveArtist(existingArtist));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        if (artistService.getArtistById(id).isPresent()) {
            artistService.deleteArtist(id);
            return ResponseEntity.ok("Deleted successfully!");

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/sortBy/{field}")
    public List<ArtistModel> getSorted(@PathVariable String field) {
        return artistService.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<ArtistModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return artistService.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<ArtistModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return artistService.pagesort(pagesize, offset,field);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ArtistModel>> getArtistByName(@PathVariable String name) {
        List<ArtistModel> employees = artistService.getArtistByName(name);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
     @PostMapping("/add")
    public ResponseEntity<String> addArtist(@RequestParam String bio, 
                                            @RequestParam String genre,
                                            @RequestParam String name) {
        artistService.saveArtist(bio, genre,name);
        return ResponseEntity.ok("Artist added successfully!");
    }

}
