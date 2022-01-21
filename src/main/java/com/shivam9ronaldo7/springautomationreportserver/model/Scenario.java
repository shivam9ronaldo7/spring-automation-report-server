package com.shivam9ronaldo7.springautomationreportserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="scenario")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToMany(mappedBy = "scenarios")
    private List<Feature> features;

    @Column
    @JsonProperty("line")
    private long line;

    @Column
    @JsonProperty("name")
    private String name;

    @Column
    @JsonProperty("description")
    private String description;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "scenario_step",
            joinColumns = @JoinColumn(name = "scenario_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id")
    )
    @JsonProperty("steps")
    private List<Step> steps;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "scenario_tag",
            joinColumns = @JoinColumn(name = "scenario_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonProperty("tags")
    private List<Tag> tags;

    @Column
    @JsonProperty("start_timestamp")
    private String startTimeStamp;

}
