package com.example.servicemanagementsystem.services.userService;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.servicemanagementsystem.Repository.UserRepository;
import com.example.servicemanagementsystem.dto.LoginRequest;
import com.example.servicemanagementsystem.Entity.User;





@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository ;
    

    public User Userlogin(LoginRequest loginRequest) {
        // System.out.println("Received email: " + loginRequest.getUsername());
        // System.out.println("Is UserRepository null? " + (userRepository == null));

             
         User dbUser =userRepository.findByEmail(loginRequest.getUsername());
        //  System.out.println("++++++++++"+dbUser);
    	 
    	 if(dbUser==null) {
    		//  System.out.println("db user is null");
    		 return null;
    	 }
    	 else if(dbUser.getPassword().equals(loginRequest.getPassword())) {
            // System.out.println("db user is not null");
    		 return dbUser;	 
    	 }
    	 return null;
     }
     
     
    
    
}
