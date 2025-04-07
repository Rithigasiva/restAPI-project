package com.examly.springapp.artistservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.artistmodel.ArtistModel;
import com.examly.springapp.artistrepo.ArtistRepo;
@Service
public class ArtistService {

    @Autowired
    private ArtistRepo artistRepository;

    public List<ArtistModel> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<ArtistModel> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    public ArtistModel saveArtist(ArtistModel artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
    public List<ArtistModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return artistRepository.findAll(sort);
    }

    public List<ArtistModel> page(int pageSize, int pageNumber) {
        PageRequest page = PageRequest.of(pageNumber, pageSize);
        return artistRepository.findAll(page).getContent();
    }

    public List<ArtistModel> pagesort(int pageSize, int pageNumber, String field) {
        return artistRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
            }
            public List<ArtistModel> getArtistByName(String name) {
        return artistRepository.findByName(name);
    }
    public void saveArtist(String bio, String genre,String name) {
        artistRepository.saveArtist(bio, genre,name);
    }
   
}
