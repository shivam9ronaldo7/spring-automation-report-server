package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.repository.ExecutionsRepository;
import com.shivam9ronaldo7.springautomationreportserver.repository.FeatureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeaturesServiceImpl implements FeatureService {

    private static final Logger LOGGER = LogManager.getLogger(FeaturesServiceImpl.class);

    @Autowired
    FeatureRepository featureRepository;

    @Override
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public void addFeature(Feature feature) {
        featureRepository.save(feature);
    }

    @Override
    public Feature getFeatureById(Long id) {
        Optional<Feature> optional = featureRepository.findById(id);
        Feature feature = null;
        if (optional.isPresent()) {
            feature = optional.get();
        } else {
            throw new RuntimeException("Feature not found for id " + id);
        }
        return feature;
    }

    @Override
    public Feature getFeatureByUri(String uri) {
        return featureRepository.findByUri(uri);
    }

    @Override
    public List<Feature> getFeaturesByName(String name) {
        return featureRepository.findByName(name);
    }

    @Override
    public List<Feature> getFeaturesByDescription(String description) {
        return featureRepository.findByDescription(description);
    }

    @Override
    public List<Feature> getFeaturesByTags(List<Tag> tags) {
        return featureRepository.findAllFeaturesByTagsIn(tags);
    }
}
