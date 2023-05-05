package com.bt.logapiconnector.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventLog {

    private String applicationId;
    private String description;
    private LocalDateTime timestamp;

}
