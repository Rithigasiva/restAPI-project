package com.examly.springapp.feedbackcontroller;
import com.examly.springapp.feedbackmodel.FeedbackModel;
import com.examly.springapp.feedbackmodel.FeedbackModel.UserType;
import com.examly.springapp.feedbackservice.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackModel> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackModel> getFeedbackById(@PathVariable Long id) {
        Optional<FeedbackModel> feedback = feedbackService.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/event/{eventName}")
    public List<FeedbackModel> getFeedbackByEvent(@PathVariable String eventName) {
        return feedbackService.getByEventName(eventName);
    }

    @GetMapping("/userType/{userType}")
    public List<FeedbackModel> getFeedbackByUserType(@PathVariable FeedbackModel.UserType userType) {
        return feedbackService.getFeedbackByUserType(userType);
    }
    
    @PostMapping
    public FeedbackModel createFeedback(@RequestBody FeedbackModel feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        if (feedbackService.getFeedbackById(id).isPresent()) {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok("Deleted successfully!");

        } else {
            return ResponseEntity.notFound().build();
        }
    }
      @GetMapping("/name/{name}")
    public ResponseEntity<List<FeedbackModel>> getFeedbackByEventName(@PathVariable String name) {
        List<FeedbackModel> feedbacks = feedbackService.getFeedbackByEventName(name);
        if (feedbacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(feedbacks);
    }
    @GetMapping("/sortBy/{field}")
    public List<FeedbackModel> getSorted(@PathVariable String field) {
        return feedbackService.sort(field);
    }

    @GetMapping("/{offset}/{pagesize}")
    public List<FeedbackModel> getPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        return feedbackService.page(pagesize, offset);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<FeedbackModel> getPaginatedSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return feedbackService.pagesort(pagesize, offset,field);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addFeedback(@RequestParam String givenBy,@RequestParam String eventName,@RequestParam String comments,@RequestParam UserType userType) {
    feedbackService.addFeedback(givenBy, eventName, comments, userType);
    return ResponseEntity.ok("Feedback submitted successfully!");
}
}
