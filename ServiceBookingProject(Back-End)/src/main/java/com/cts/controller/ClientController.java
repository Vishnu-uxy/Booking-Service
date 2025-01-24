package com.example.servicemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicemanagementsystem.dto.ReservationDTO;
import com.example.servicemanagementsystem.services.client.ClientService;
import org.springframework.web.bind.annotation.PostMapping;






@RestController
@RequestMapping("/api/client")
public class ClientController {


        @Autowired
        private ClientService clientService;


        @GetMapping("/ads")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<?> getAllAds(){
            return ResponseEntity.ok(clientService.getAllAds());
        }

       
        @GetMapping("/search/{name}")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<?> searchAdByService(@PathVariable String name){
            return  ResponseEntity.ok(clientService.searchAdByName(name));

        }

        @GetMapping("/searchCategory/{category}")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<?> searchAdByCategory(@PathVariable String category){
            return  ResponseEntity.ok(clientService.searchAdByCategory(category));

        }


        @PostMapping("/book-service")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO){
            boolean success = clientService.bookService(reservationDTO);
            if(success){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        @GetMapping("/ad/{adId}")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){
            return ResponseEntity.ok(clientService.getAdDetailsForClientDTO(adId));
        }


            @GetMapping("/my-bookings/{userId}")
            @CrossOrigin(origins = "http://localhost:4200")
            public  ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
                return ResponseEntity.ok(clientService.getAllBookingsByUserId(userId));
            }







    
}
