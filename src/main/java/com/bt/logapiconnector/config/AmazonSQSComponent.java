package com.bt.logapiconnector.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AmazonSQSComponent {


    private final AmazonSQSAsync amazonSQSAsync;
    private final String queueUrl;

    public AmazonSQSComponent(AmazonSQSAsync amazonSQSAsync, @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.amazonSQSAsync = amazonSQSAsync;
        this.queueUrl = queueUrl;
    }

    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody);
        amazonSQSAsync.sendMessage(sendMessageRequest);
    }

}
