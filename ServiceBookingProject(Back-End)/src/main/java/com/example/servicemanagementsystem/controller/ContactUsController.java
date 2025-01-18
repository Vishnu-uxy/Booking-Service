package com.example.servicemanagementsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicemanagementsystem.Entity.ContactUs;
import com.example.servicemanagementsystem.services.contactUs.ContactUsService;



@RestController
@RequestMapping("/contact")
public class ContactUsController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	public void contactus() {
				
	}
	
	@PostMapping()
	@CrossOrigin(origins ="http://localhost:4200/")
	public String saveRequest(@RequestBody ContactUs contactUs) {
		System.out.println(contactUs.getMessage()+"THis is working");	
		return contactUsService.postContact(contactUs);
		
		
		
	}
	

}

