package com.bt.logapiconnector.config;

public interface AmazonSQSComponent {

    void sendMessage(String messageBody);

}
