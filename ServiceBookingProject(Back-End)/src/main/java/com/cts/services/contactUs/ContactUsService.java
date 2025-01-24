package com.example.servicemanagementsystem.services.contactUs;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicemanagementsystem.Entity.ContactUs;
import com.example.servicemanagementsystem.Repository.ContactUsRepo;;



@Service
public class ContactUsService {
	
	
	@Autowired
	private ContactUsRepo contactUsRepo;
	
	
	public void getContact() {		
		contactUsRepo.findAll();
		
	}
	
	
	public String postContact(ContactUs contactUs) {	
		contactUsRepo.save(contactUs);
		return "Succcessfull";
				
	}
	
}