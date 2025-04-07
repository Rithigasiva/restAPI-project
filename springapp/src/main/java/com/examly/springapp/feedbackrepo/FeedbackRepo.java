package com.examly.springapp.feedbackrepo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.examly.springapp.feedbackmodel.FeedbackModel;

public interface FeedbackRepo extends JpaRepository<FeedbackModel,Long> {
  List<FeedbackModel> findByEventName(String eventName);
  List<FeedbackModel> findByUserType(FeedbackModel.UserType userType);
  @Query("SELECT a FROM FeedbackModel a WHERE a.eventName = :name")
  List<FeedbackModel> findByName(@Param("name") String name);
  
  @Query("SELECT a FROM FeedbackModel a")
  List<FeedbackModel> getAllFeedbacks();
  
  @Query("SELECT a FROM FeedbackModel a WHERE a.id = :id")
  FeedbackModel getFeedbackById(@Param("id") Long id);
  
  
  @Modifying
    @Transactional
    @Query(value = "INSERT INTO feedbacks (given_by, event_name, comments, user_type) VALUES (:givenBy, :eventName, :comments, :userType)", nativeQuery = true)
    void insertFeedback(@Param("givenBy") String givenBy,@Param("eventName") String eventName,@Param("comments") String comments,@Param("userType") String userType);
}

   