package com.shivam9ronaldo7.springautomationreportserver.repository;

import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.model.Scenario;
import com.shivam9ronaldo7.springautomationreportserver.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

    List<Scenario> findScenariosById(Long id);
    List<Scenario> findScenariosByName(String name);

}
