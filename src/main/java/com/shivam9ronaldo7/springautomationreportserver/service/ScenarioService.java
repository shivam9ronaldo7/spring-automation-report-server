package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;

import java.util.List;
import java.util.Set;

public interface ScenarioService {
    List<Scenario> getAllScenarios();
    void addScenario(Scenario scenario);
    Scenario getScenarioById(Long scenarioId);
    List<Scenario> getScenariosByName(String name);
    List<Scenario> getScenariosByDescription(String description);
    List<Scenario> getScenariosByTags(List<Tag> tags);
}
