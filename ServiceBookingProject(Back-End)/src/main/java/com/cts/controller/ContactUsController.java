package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Entity.ContactUs;
import com.cts.services.contactUs.ContactUsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/contact")
public class ContactUsController {

    private static final Logger logger = LoggerFactory.getLogger(ContactUsController.class);

    @Autowired
    private ContactUsService contactUsService;

    public void contactus() {
        // No implementation needed
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:4200/")
    public String saveRequest(@RequestBody ContactUs contactUs) {
        logger.info("Received contact us request with message: {}", contactUs.getMessage());
        System.out.println(contactUs.getMessage() + " This is working");
        String response = contactUsService.postContact(contactUs);
        logger.info("Contact us request processed with response: {}", response);
        return response;
    }
}
