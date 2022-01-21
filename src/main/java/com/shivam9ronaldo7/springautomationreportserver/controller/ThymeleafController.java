package com.shivam9ronaldo7.springautomationreportserver.controller;

import com.shivam9ronaldo7.springautomationreportserver.configuration.FileUploadProperties;
import com.shivam9ronaldo7.springautomationreportserver.service.ExecutionsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    private static final Logger LOGGER = LogManager.getLogger(ThymeleafController.class);

    @Autowired
    ExecutionsServiceImpl executionsServiceImpl;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("listFeatures", executionsServiceImpl.getAllExecutions());
        return "listFeatures";
    }

}
