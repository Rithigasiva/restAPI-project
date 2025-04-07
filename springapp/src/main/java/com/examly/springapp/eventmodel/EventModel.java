package com.examly.springapp.eventmodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tomcat.jni.Address;

import com.examly.springapp.artistmodel.ArtistModel;
import com.examly.springapp.guestmodel.GuestModel;
import com.examly.springapp.taskmodel.TaskModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<TaskModel>task=new ArrayList<>();

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<GuestModel>guest=new ArrayList<>();

     @OneToOne(cascade = CascadeType.ALL)
     @JsonBackReference
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private ArtistModel artist;


    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getEventName() {
      return eventName;
    }
    public void setEventName(String eventName) {
      this.eventName = eventName;
    }
    public List<TaskModel> getTask() {
      return task;
    }
    public void setTask(List<TaskModel> task)
    {
          for(TaskModel tasks:task)
          {
            tasks.setEvent(this);
          }
          this.task.addAll(task);
    }
    public List<GuestModel> getGuest() 
    {
      return guest;
    }
    public void setGuest(List<GuestModel> guest)
    {
      for(GuestModel guests:guest)
      {
        guests.setEvent(this);
      }
      this.guest.addAll(guest);
    }
    public ArtistModel getArtist() {
      return artist;
    }
    public void setArtist(ArtistModel artist) {
      this.artist = artist;
    }
   
    
  }