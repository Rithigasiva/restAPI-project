package com.examly.springapp.feedbackmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.examly.springapp.guestmodel.GuestModel;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String givenBy; 
        private String eventName; 
        private String comments;
    
        @Enumerated(EnumType.STRING)

        @Column(nullable = false)
        private UserType userType; 
    
        public enum UserType {
            VENDOR, ARTIST, GUEST, EMPLOYEE
        }
        @ManyToOne
        @JoinColumn(name="guest_id")
        @JsonBackReference
        private GuestModel guest;
        
        
    }
    
