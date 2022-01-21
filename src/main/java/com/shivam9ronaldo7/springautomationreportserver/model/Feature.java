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
@Table(name="feature")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToMany(mappedBy = "features")
    private List<Execution> executions;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "feature_scenario",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "scenario_id")
    )
    @JsonProperty("elements")
    private List<Scenario> scenarios;

    @Column
    @JsonProperty("uri")
    private String uri;

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
            name = "feature_tag",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonProperty("tags")
    private List<Tag> tags;

}
