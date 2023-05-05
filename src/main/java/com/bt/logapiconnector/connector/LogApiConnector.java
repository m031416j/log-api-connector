package com.bt.logapiconnector.connector;

import com.bt.logapiconnector.model.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LogApiConnector {

    void publishLogMessage(EventLog eventLog) throws JsonProcessingException;
}
