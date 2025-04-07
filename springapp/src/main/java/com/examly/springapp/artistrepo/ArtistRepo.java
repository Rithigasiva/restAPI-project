package com.examly.springapp.artistrepo;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.examly.springapp.artistmodel.ArtistModel;
public interface ArtistRepo extends JpaRepository<ArtistModel,Long> {
    
    @Query("SELECT a FROM ArtistModel a WHERE a.name = :name")
    List<ArtistModel> findByName(@Param("name") String name);

    @Query("SELECT a FROM ArtistModel a")
    List<ArtistModel> getAllArtists();

    @Query("SELECT a FROM ArtistModel a WHERE a.id = :id")
    ArtistModel getArtistById(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO artists(bio,genre,name) VALUES (:bio, :genre,:name)", nativeQuery = true)
    void saveArtist(@Param("bio") String bio, @Param("genre") String genre,@Param("name") String name);

}
