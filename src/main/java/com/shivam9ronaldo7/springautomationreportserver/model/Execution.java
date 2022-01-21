package com.shivam9ronaldo7.springautomationreportserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="execution")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long executionId;

    @Column
    private String jenkinsExecutionStartTime;

    @Column
    private long jenkinsBuildNumber;

    @Column
    private String startedByUser;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "executions_features",
            joinColumns = @JoinColumn(name = "executionId"),
            inverseJoinColumns = @JoinColumn(name = "featureId")
    )
    private List<Feature> features;

}
