package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.repository.ExecutionsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutionsServiceImpl implements ExecutionService {

    private static final Logger LOGGER = LogManager.getLogger(ExecutionsServiceImpl.class);

    @Autowired
    ExecutionsRepository executionsRepository;

    @Override
    public List<Execution> getAllExecutions() {
        return executionsRepository.findAll();
    }

    @Override
    public void addExecution(Execution execution) {
        executionsRepository.save(execution);
    }

    @Override
    public Execution getExecutionById(Long id) {
        Optional<Execution> optional = executionsRepository.findById(id);
        Execution execution = null;
        if (optional.isPresent()) {
            execution = optional.get();
        } else {
            throw new RuntimeException("Execution not found for id " + id);
        }
        return execution;
    }

    @Override
    public List<Execution> getExecutionByStartedByUser(String startedByUser) {
        return executionsRepository.findByStartedByUser(startedByUser);
    }
}
