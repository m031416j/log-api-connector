package com.bt.logapiconnector.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmazonSQSComponentImpl implements AmazonSQSComponent {

    private final AmazonSQSAsync amazonSQSAsync;

    public AmazonSQSComponentImpl(AmazonSQSAsync amazonSQSAsync) {
        this.amazonSQSAsync = amazonSQSAsync;

    }

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody);
        amazonSQSAsync.sendMessage(sendMessageRequest);
    }

}
