package com.shivam9ronaldo7.springautomationreportserver.repository;

import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsTagByName(String tagName);
    List<Scenario> findScenariosByNameIn(List<String> tagNameList);
    List<Feature> findFeaturesByNameIn(List<String> tagNameList);

}
