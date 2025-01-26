package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.ReservationDTO;
import com.cts.exceptions.ResourceNotFoundException;
import com.cts.services.client.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllAds() {
        logger.info("Received request to get all ads");
        return ResponseEntity.ok(clientService.getAllAds());
    }

    @GetMapping("/search/{name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> searchAdByService(@PathVariable String name) {
        logger.info("Received request to search ad by service name: {}", name);
        return ResponseEntity.ok(clientService.searchAdByName(name));
    }

    @GetMapping("/searchCategory/{category}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> searchAdByCategory(@PathVariable String category) {
        logger.info("Received request to search ad by category: {}", category);
        return ResponseEntity.ok(clientService.searchAdByCategory(category));
    }

    @PostMapping("/book-service")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO) {
        logger.info("Received request to book service for reservation: {}", reservationDTO);
        boolean success = clientService.bookService(reservationDTO);
        if (success) {
            logger.info("Service booking successful for reservation: {}", reservationDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.warn("Service booking failed for reservation: {}", reservationDTO);
            throw new ResourceNotFoundException("Service booking failed for reservation: " + reservationDTO);
        }
    }

    @GetMapping("/ad/{adId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId) {
        logger.info("Received request to get ad details for adId: {}", adId);
        var adDetails = clientService.getAdDetailsForClientDTO(adId);
        if (adDetails != null) {
            logger.info("Ad details retrieved successfully for adId: {}", adId);
            return ResponseEntity.ok(adDetails);
        } else {
            logger.warn("No ad found for adId: {}", adId);
            throw new ResourceNotFoundException("No ad found for adId: " + adId);
        }
    }

    @GetMapping("/my-bookings/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId) {
        logger.info("Received request to get all bookings for userId: {}", userId);
        var bookings = clientService.getAllBookingsByUserId(userId);
        if (bookings != null && !bookings.isEmpty()) {
            return ResponseEntity.ok(bookings);
        } else {
            throw new ResourceNotFoundException("No bookings found for userId: " + userId);
        }
    }
}
