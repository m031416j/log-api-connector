package com.bt.logapiconnector.connector;

import com.bt.logapiconnector.config.AmazonSQSComponent;
import com.bt.logapiconnector.exception.LogApiConnectorException;
import com.bt.logapiconnector.model.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;

@Component
@Slf4j
public class LogApiConnectorImpl implements LogApiConnector {

    @Autowired
    private AmazonSQSComponent amazonSQSComponent;

    public void publishLogMessage(EventLog eventLog) throws JsonProcessingException {
        if(eventLog == null) {
            throw new LogApiConnectorException("EventLog object is null");
        }
        amazonSQSComponent.sendMessage(mapEventLogToString(eventLog));
    }

    private String mapEventLogToString(EventLog eventLog) throws JsonProcessingException {

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(DateFormat.getDateInstance());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        return mapper.writeValueAsString(eventLog);

    }

}
