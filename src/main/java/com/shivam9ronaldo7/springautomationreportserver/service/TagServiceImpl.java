package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.repository.TagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LogManager.getLogger(TagServiceImpl.class);

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public List<Scenario> getScenariosByTagNames(List<String> tagsNames) {
        return tagRepository.findScenariosByNameIn(tagsNames);
    }

    @Override
    public List<Feature> getFeaturesByTagNames(List<String> tagsNames) {
        return tagRepository.findFeaturesByNameIn(tagsNames);
    }
}
