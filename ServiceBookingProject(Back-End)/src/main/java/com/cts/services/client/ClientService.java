package com.cts.services.client;

import java.util.List;

import com.cts.dto.AdDTO;
import com.cts.dto.AdDetailsForClientDTO;
import com.cts.dto.ReservationDTO;

public interface ClientService {
    
     List<AdDTO> getAllAds();
     List<AdDTO> searchAdByName(String name);
     List<AdDTO> searchAdByCategory(String name);
     boolean bookService(ReservationDTO reservationDTO);
     AdDetailsForClientDTO getAdDetailsForClientDTO(Long adId);

     List<ReservationDTO> getAllBookingsByUserId(Long userId);
}

