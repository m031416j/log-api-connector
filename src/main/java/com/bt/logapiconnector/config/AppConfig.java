package com.bt.logapiconnector.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AmazonSQSComponent amazonSQSComponent(AmazonSQSAsync amazonSQSAsync) {
        return new AmazonSQSComponentImpl(amazonSQSAsync);

    }
}
