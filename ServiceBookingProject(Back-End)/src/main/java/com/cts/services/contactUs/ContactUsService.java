package com.cts.services.contactUs;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Entity.ContactUs;
import com.cts.Repository.ContactUsRepo;;



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