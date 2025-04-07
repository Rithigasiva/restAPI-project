package com.examly.springapp.feedbackservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.examly.springapp.feedbackmodel.FeedbackModel;
import com.examly.springapp.feedbackmodel.FeedbackModel.UserType;
import com.examly.springapp.feedbackrepo.FeedbackRepo;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepository;

    public List<FeedbackModel> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<FeedbackModel> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public List<FeedbackModel> getByEventName(String eventName) {
        return feedbackRepository.findByEventName(eventName);
    }

    public List<FeedbackModel> getFeedbackByUserType(FeedbackModel.UserType userType) {
        return feedbackRepository.findByUserType(userType);
    }

    public FeedbackModel saveFeedback(FeedbackModel feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
        public List<FeedbackModel> getFeedbackByEventName(String name) {
        return feedbackRepository.findByName(name);
    }
    public List<FeedbackModel> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return feedbackRepository.findAll(sort);
    }

    public List<FeedbackModel> page(int pageSize, int pageNumber) {
        PageRequest page = PageRequest.of(pageNumber, pageSize);
        return feedbackRepository.findAll(page).getContent();
    }

    public List<FeedbackModel> pagesort(int pageSize, int pageNumber, String field) {
        return feedbackRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
            }
    public void addFeedback(String givenBy, String eventName, String comments, UserType userType) {
        feedbackRepository.insertFeedback(givenBy, eventName, comments, userType.name());
    }
}

