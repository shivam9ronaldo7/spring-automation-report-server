package com.shivam9ronaldo7.springautomationreportserver.repository;

import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.service.TagService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    List<Scenario> findByName(String name);
    List<Scenario> findByDescription(String name);
    List<Scenario> findAllScenariosByTagsIn(List<Tag> tags);

}
