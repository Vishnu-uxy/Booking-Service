package com.cts.services.company;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Entity.Ad;
import com.cts.Entity.Reservation;
import com.cts.Entity.User;
import com.cts.Repository.AdRepository;
import com.cts.Repository.ReservationRepository;
import com.cts.Repository.UserRepository;
import com.cts.dto.AdDTO;
import com.cts.dto.ReservationDTO;
import com.cts.enums.ReservationStatus;

@Service
public class CompanyServiceImpl implements CompanyService {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setCategory(adDTO.getCategory());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDTO> getAllAds(Long userId) {
        return adRepository.findAllByuserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public AdDTO getAdById(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            return optionalAd.get().getAdDto();
        }
        return null;
    }

    public boolean updateAd(Long adId, AdDTO adDTO) throws IOException{
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            Ad ad = optionalAd.get();
            System.out.println("-----"+ad);
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setPrice(adDTO.getPrice());

            if(adDTO.getImg() != null){
                ad.setImg(adDTO.getImg().getBytes());
            }

            adRepository.save(ad);
            return true;
        }else{
            return false;
        
    }  }


        public boolean deleteAd(long adId){
                Optional<Ad> optionalAd=adRepository.findById(adId);
                if(optionalAd.isPresent()){
                    adRepository.delete(optionalAd.get());
                    return true;
                }
                return false;


        }


        public List<ReservationDTO> getAllAdBookings(Long companyId){
         return  reservationRepository.findAllByCompanyId(companyId)
         .stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
        }


         public boolean changeBookingStatus(Long bookingId,String status){
            Optional <Reservation> optionalReservation=reservationRepository.findById(bookingId);
            if(optionalReservation.isPresent()){
                Reservation existingReservation=optionalReservation.get();
                    if(Objects.equals(status,"Approve")){
                        existingReservation.setReservationStatus(ReservationStatus.APPROVED);
                    }
                    else{
                        existingReservation.setReservationStatus(ReservationStatus.REJECTED);
                    }

                    reservationRepository.save(existingReservation);
                    return true;
            }
            return false;

      }

     

}








