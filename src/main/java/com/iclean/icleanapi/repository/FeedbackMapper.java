package com.iclean.icleanapi.repository;

import com.iclean.icleanapi.dto.Feedback;
import com.iclean.icleanapi.dto.FeedbackForm;
import com.iclean.icleanapi.dto.FeedbackRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    List<Feedback> getFeedback(FeedbackForm form);
    boolean createFeedback(FeedbackRequest form);
}
