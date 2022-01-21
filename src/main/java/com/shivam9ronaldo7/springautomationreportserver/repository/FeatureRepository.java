package com.shivam9ronaldo7.springautomationreportserver.repository;

import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.service.TagService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    Feature findByUri(String uri);
    List<Feature> findByName(String name);
    List<Feature> findByDescription(String description);
    List<Feature> findAllFeaturesByTagsIn(List<Tag> tags);

}
