package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;

import java.util.List;
import java.util.Set;

public interface FeatureService {

    List<Feature> getAllFeatures();
    void addFeature(Feature feature);
    Feature getFeatureById(Long id);
    Feature getFeatureByUri(String uri);
    List<Feature> getFeaturesByName(String name);
    List<Feature> getFeaturesByDescription(String description);
    List<Feature> getFeaturesByTags(List<Tag> tags);

}
