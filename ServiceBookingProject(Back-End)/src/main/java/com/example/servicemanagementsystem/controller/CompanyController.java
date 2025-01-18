package com.example.servicemanagementsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicemanagementsystem.dto.AdDTO;
import com.example.servicemanagementsystem.dto.ReservationDTO;
import com.example.servicemanagementsystem.services.company.CompanyService;



@RestController
@RequestMapping("/api/company")
public class CompanyController {

     @Autowired
    private CompanyService companyService;
    
    @PostMapping("/ad/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
    
        boolean success = companyService.postAd(userId, adDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/ads/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAdById(@PathVariable Long adId) {
        AdDTO adDTO = companyService.getAdById(adId);
        if (adDTO != null) {
            return ResponseEntity.ok(adDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateAd(@PathVariable Long adId, @ModelAttribute AdDTO adDTO) throws IOException{
        boolean success = companyService.updateAd(adId, adDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        }


    @DeleteMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId){
        boolean success=companyService.deleteAd(adId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/bookings/{companyId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ReservationDTO>> getAllAdBookings(@PathVariable Long companyId){
     return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
}


    @GetMapping("/booking/{bookingId}/{status}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId,@PathVariable String status){
        boolean success=companyService.changeBookingStatus(bookingId, status);
        if(success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }







}
