package com.cts.controller;

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

import com.cts.dto.AdDTO;
import com.cts.dto.ReservationDTO;
import com.cts.exceptions.ResourceNotFoundException;
import com.cts.services.company.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;
    
    @PostMapping("/ad/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        logger.info("Received request to post ad for userId: {}", userId);
        boolean success = companyService.postAd(userId, adDTO);
        if (success) {
            logger.info("Ad posted successfully for userId: {}", userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.warn("Failed to post ad for userId: {}", userId);
            throw new ResourceNotFoundException("Failed to post ad for userId: " + userId);
        }
    }

    @GetMapping("/ads/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId) {
        logger.info("Received request to get all ads for userId: {}", userId);
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAdById(@PathVariable Long adId) {
        logger.info("Received request to get ad details for adId: {}", adId);
        AdDTO adDTO = companyService.getAdById(adId);
        if (adDTO != null) {
            logger.info("Ad details retrieved successfully for adId: {}", adId);
            return ResponseEntity.ok(adDTO);
        } else {
            logger.warn("No ad found for adId: {}", adId);
            throw new ResourceNotFoundException("No ad found for adId: " + adId);
        }
    }

    @PutMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateAd(@PathVariable Long adId, @ModelAttribute AdDTO adDTO) throws IOException {
        logger.info("Received request to update ad for adId: {}", adId);
        boolean success = companyService.updateAd(adId, adDTO);
        if (success) {
            logger.info("Ad updated successfully for adId: {}", adId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.warn("Failed to update ad for adId: {}", adId);
            throw new ResourceNotFoundException("Failed to update ad for adId: " + adId);
        }
    }

    @DeleteMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId) {
        logger.info("Received request to delete ad for adId: {}", adId);
        boolean success = companyService.deleteAd(adId);
        if (success) {
            logger.info("Ad deleted successfully for adId: {}", adId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.warn("Failed to delete ad for adId: {}", adId);
            throw new ResourceNotFoundException("Failed to delete ad for adId: " + adId);
        }
    }

    @GetMapping("/bookings/{companyId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ReservationDTO>> getAllAdBookings(@PathVariable Long companyId) {
        logger.info("Received request to get all bookings for companyId: {}", companyId);
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @GetMapping("/booking/{bookingId}/{status}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        logger.info("Received request to change booking status for bookingId: {}, status: {}", bookingId, status);
        boolean success = companyService.changeBookingStatus(bookingId, status);
        if (success) {
            logger.info("Booking status changed successfully for bookingId: {}, status: {}", bookingId, status);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Failed to change booking status for bookingId: {}, status: {}", bookingId, status);
            throw new ResourceNotFoundException("Failed to change booking status for bookingId: " + bookingId + ", status: " + status);
        }
    }
}
