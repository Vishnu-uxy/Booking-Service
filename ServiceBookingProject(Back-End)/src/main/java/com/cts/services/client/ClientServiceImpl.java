package com.cts.services.client;


import java.util.List;
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
import com.cts.dto.AdDetailsForClientDTO;
import com.cts.dto.ReservationDTO;
import com.cts.enums.ReservationStatus;
import com.cts.enums.ReviewStatus;

@Service
public class ClientServiceImpl implements ClientService {
    
        @Autowired
        private AdRepository adRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ReservationRepository reservationRepository;

        public List<AdDTO> getAllAds(){

            return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
        }

        public List<AdDTO> searchAdByName(String name){
            return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
        }
        public List<AdDTO> searchAdByCategory(String category){
                return adRepository.findAllByCategory(category).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

        public boolean bookService(ReservationDTO reservationDTO){
            Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
            Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

            if(optionalAd.isPresent() && optionalUser.isPresent()){
                Reservation reservation = new Reservation();
                reservation.setBookDate(reservationDTO.getBookDate());
                reservation.setReservationStatus(ReservationStatus.PENDING);
                reservation.setUser((optionalUser.get()));
                reservation.setAd(optionalAd.get());
                reservation.setCompany(optionalAd.get().getUser());
                reservation .setReviewStatus((ReviewStatus.FALSE));

                reservationRepository.save(reservation);
                return true;
            }
            return false;
        }

        public AdDetailsForClientDTO getAdDetailsForClientDTO(Long adId){
            Optional <Ad> optionalAd = adRepository.findById((adId));
            AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
            if(optionalAd.isPresent()){
                adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDto());

            }
            return adDetailsForClientDTO;
        }

        public List<ReservationDTO> getAllBookingsByUserId(Long userId){
            return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
      }
}












