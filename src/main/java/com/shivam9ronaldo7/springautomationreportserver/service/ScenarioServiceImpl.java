package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.repository.FeatureRepository;
import com.shivam9ronaldo7.springautomationreportserver.repository.ScenarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ScenarioServiceImpl implements ScenarioService {

    private static final Logger LOGGER = LogManager.getLogger(ScenarioServiceImpl.class);

    @Autowired
    ScenarioRepository scenarioRepository;

    @Override
    public List<Scenario> getAllScenarios() {
        return scenarioRepository.findAll();
    }

    @Override
    public void addScenario(Scenario scenario) {
        scenarioRepository.save(scenario);
    }

    @Override
    public Scenario getScenarioById(Long id) {
        Optional<Scenario> optional = scenarioRepository.findById(id);
        Scenario scenario = null;
        if (optional.isPresent()) {
            scenario = optional.get();
        } else {
            throw new RuntimeException("Scenario not found for id " + id);
        }
        return scenario;
    }

    @Override
    public List<Scenario> getScenariosByName(String name) {
        return scenarioRepository.findByName(name);
    }

    @Override
    public List<Scenario> getScenariosByDescription(String description) {
        return scenarioRepository.findByDescription(description);
    }

    @Override
    public List<Scenario> getScenariosByTags(List<Tag> tags) {
        return scenarioRepository.findAllScenariosByTagsIn(tags);
    }

}
