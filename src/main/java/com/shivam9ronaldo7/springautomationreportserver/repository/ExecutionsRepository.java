package com.shivam9ronaldo7.springautomationreportserver.repository;

import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionsRepository extends JpaRepository<Execution, Long> {

    List<Execution> findByStartedByUser(String startedByUser);

}
