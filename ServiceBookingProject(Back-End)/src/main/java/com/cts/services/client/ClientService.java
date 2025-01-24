package com.example.servicemanagementsystem.services.client;

import java.util.List;

import com.example.servicemanagementsystem.dto.AdDTO;
import com.example.servicemanagementsystem.dto.AdDetailsForClientDTO;
import com.example.servicemanagementsystem.dto.ReservationDTO;

public interface ClientService {
    
     List<AdDTO> getAllAds();
     List<AdDTO> searchAdByName(String name);
     List<AdDTO> searchAdByCategory(String name);
     boolean bookService(ReservationDTO reservationDTO);
     AdDetailsForClientDTO getAdDetailsForClientDTO(Long adId);

     List<ReservationDTO> getAllBookingsByUserId(Long userId);
}

