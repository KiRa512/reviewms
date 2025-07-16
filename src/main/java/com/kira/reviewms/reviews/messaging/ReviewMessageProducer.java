package com.kira.reviewms.reviews.messaging;

import com.kira.reviewms.reviews.Review;
import com.kira.reviewms.reviews.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setContent(review.getContent());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage); // send the message to the queue
    }
}
