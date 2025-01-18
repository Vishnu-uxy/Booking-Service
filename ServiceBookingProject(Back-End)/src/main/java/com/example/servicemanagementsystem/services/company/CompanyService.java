package com.example.servicemanagementsystem.services.company;

import java.io.IOException;
import java.util.List;

import com.example.servicemanagementsystem.dto.AdDTO;
import com.example.servicemanagementsystem.dto.ReservationDTO;

public interface CompanyService {
    

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException;
    
    List<AdDTO> getAllAds(Long userId);
    
    AdDTO getAdById(Long adId);

    boolean deleteAd(long adId);

    boolean updateAd(Long adId, AdDTO adDTO) throws IOException;

    List<ReservationDTO> getAllAdBookings(Long companyId);

    boolean changeBookingStatus(Long bookingId,String status);

}
