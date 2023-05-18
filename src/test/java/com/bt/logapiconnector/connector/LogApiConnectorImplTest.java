package com.bt.logapiconnector.connector;

import com.bt.logapiconnector.config.AmazonSQSComponent;
import com.bt.logapiconnector.exception.LogApiConnectorException;
import com.bt.logapiconnector.model.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class LogApiConnectorImplTest {

    @InjectMocks
    private LogApiConnectorImpl logApiConnector;

    @Mock
    private AmazonSQSComponent amazonSQSComponent;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When given a valid event log then send the message")
    void testPublishLogMessageSuccess() throws JsonProcessingException {
        // given
        EventLog log = new EventLog("ID", "DESC", LocalDateTime.now());

        // when
        doNothing().when(amazonSQSComponent).sendMessage(any());
        logApiConnector.publishLogMessage(log);

        // then
        verify(amazonSQSComponent, times(1)).sendMessage(any());

    }

    @Test
    @DisplayName("When given an invalid event log then throw exception")
    void testPublishLogMessageThrowException  () throws JsonProcessingException {

        // when // then

        assertThrows(LogApiConnectorException.class, () -> logApiConnector.publishLogMessage(null));

    }


}