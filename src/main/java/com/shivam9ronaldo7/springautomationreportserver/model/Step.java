package com.shivam9ronaldo7.springautomationreportserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="step")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "steps")
    private List<Scenario> scenarios;

    @Column
    @JsonProperty("line")
    private long line;

    @Column
    @JsonProperty("keyword")
    private String keyword;

    @Embedded
    @JsonProperty("result")
    private StepResult stepResult;

    @Column
    @JsonProperty("name")
    private String name;

}
