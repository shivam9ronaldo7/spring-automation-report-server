package com.shivam9ronaldo7.springautomationreportserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepResult {

    @JsonProperty("duration")
    private long duration;

    @JsonProperty("status")
    private String status;

}
