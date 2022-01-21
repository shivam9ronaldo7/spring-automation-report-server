package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Step;

import java.util.List;
import java.util.Set;

public interface StepService {
    List<Step> getAllSteps();
    void addStep(Step step);
    List<Scenario> getScenariosByStepId(Long stepId);
    List<Scenario> getScenariosByName(String name);
}
