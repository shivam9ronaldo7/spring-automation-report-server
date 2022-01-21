package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Step;
import com.shivam9ronaldo7.springautomationreportserver.repository.StepRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StepServiceImpl implements StepService {

    private static final Logger LOGGER = LogManager.getLogger(StepServiceImpl.class);

    @Autowired
    StepRepository stepRepository;

    @Override
    public List<Step> getAllSteps() {
        return stepRepository.findAll();
    }

    @Override
    public void addStep(Step step) {
        stepRepository.save(step);
    }

    @Override
    public List<Scenario> getScenariosByStepId(Long id) {
        return stepRepository.findScenariosById(id);
    }

    @Override
    public List<Scenario> getScenariosByName(String step) {
        return stepRepository.findScenariosByName(step);
    }

}
