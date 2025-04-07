package com.examly.springapp.guestmodel;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.jni.Address;

import com.examly.springapp.eventmodel.EventModel;
import com.examly.springapp.feedbackmodel.FeedbackModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guests")
@AllArgsConstructor
@NoArgsConstructor
public class GuestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String name;
    @ManyToOne
    @JoinColumn(name="event_id")
    @JsonBackReference
    private EventModel event;
    
    @OneToMany(mappedBy = "guest",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<FeedbackModel>feedback=new ArrayList<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public EventModel getEvent() {
        return event;
    }
    public void setEvent(EventModel event) {
        this.event = event;
    }
    public List<FeedbackModel> getFeedback() {
        return feedback;
    }
    public void setFeedback(List<FeedbackModel> feedback) {
        for(FeedbackModel feedbacks : feedback )
        {
            feedbacks.setGuest(this);
        }
        this.feedback.addAll(feedback);
    }
   
}
