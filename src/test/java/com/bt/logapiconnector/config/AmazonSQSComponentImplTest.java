package com.bt.logapiconnector.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AmazonSQSComponentImplTest {


    @Mock
    private AmazonSQSAsync amazonSQSAsync;

    @InjectMocks
    private AmazonSQSComponentImpl amazonSQSComponent;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When given a valid message then send the message")
    void testSendMessageSuccess() {
        // given
        String message = "message";

        // when
        when(amazonSQSAsync.sendMessage(any(SendMessageRequest.class))).thenReturn(new SendMessageResult());
        amazonSQSComponent.sendMessage(message);

        // then
        verify(amazonSQSAsync, times(1)).sendMessage(any());
    }


}