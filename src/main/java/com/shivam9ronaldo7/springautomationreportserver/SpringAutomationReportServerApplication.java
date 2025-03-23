package com.shivam9ronaldo7.springautomationreportserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.model.Execution;
import com.shivam9ronaldo7.springautomationreportserver.model.Feature;
import com.shivam9ronaldo7.springautomationreportserver.model.Tag;
import com.shivam9ronaldo7.springautomationreportserver.repository.ExecutionsRepository;
import com.shivam9ronaldo7.springautomationreportserver.repository.FeatureRepository;
import com.shivam9ronaldo7.springautomationreportserver.repository.TagRepository;
import com.shivam9ronaldo7.springautomationreportserver.service.ExecutionsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadProperties.class})
public class SpringAutomationReportServerApplication {

	private static final Logger LOGGER = LogManager.getLogger(SpringAutomationReportServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringAutomationReportServerApplication.class, args);
	}

}

@Component
class AutomationReportCommandLineRunner implements CommandLineRunner{

	private static final Logger LOGGER = LogManager.getLogger(AutomationReportCommandLineRunner.class);

	@Autowired
	ExecutionsServiceImpl executionsServiceImpl;

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.debug("Started reading");
		List<Feature> features = Arrays.asList(mapper.readValue(new File("D:\\GitRepos\\spring-automation-report-server\\src\\main\\resources\\static\\test.json"), Feature[].class));
		Execution execution1 = new Execution();
		execution1.setFeatures(features);
		LOGGER.debug("Started uploading");
		executionsServiceImpl.addExecution(execution1);
	}

}
