package com.shivam9ronaldo7.springautomationreportserver.controller;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.service.ExecutionsServiceImpl;
import com.shivam9ronaldo7.springautomationreportserver.service.FeaturesServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ThymeleafController {

    private static final Logger LOGGER = LogManager.getLogger(ThymeleafController.class);

    @Autowired
    ExecutionsServiceImpl executionsServiceImpl;

    @Autowired
    FeaturesServiceImpl featuresServiceImpl;

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("listExecutions", executionsServiceImpl.getAllExecutions());
        return "index";
    }

    @GetMapping("/{executionId:.+}/features")
    public String showAll(@PathVariable String executionId, Model model) {
        model.addAttribute("listFeatures", executionsServiceImpl
                .getExecutionById(Long.valueOf(executionId)).getFeatures());
        return "feature";
    }

}
