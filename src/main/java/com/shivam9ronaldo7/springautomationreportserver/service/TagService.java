package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getAllTags();
    void addTag(Tag tag);
    List<Scenario> getScenariosByTagNames(List<String> tagsNames);
    List<Feature> getFeaturesByTagNames(List<String> tagsNames);
}
