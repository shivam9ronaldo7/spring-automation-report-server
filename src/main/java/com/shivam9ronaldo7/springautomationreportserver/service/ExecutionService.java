package com.shivam9ronaldo7.springautomationreportserver.service;

import com.shivam9ronaldo7.springautomationreportserver.model.Execution;

import java.util.List;
import java.util.Optional;

public interface ExecutionService {

    List<Execution> getAllExecutions();
    void addExecution(Execution execution);
    Execution getExecutionById(Long executionId);
    List<Execution> getExecutionByStartedByUser(String startedByUser);

}
