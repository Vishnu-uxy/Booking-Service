package com.example.servicemanagementsystem.Entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.servicemanagementsystem.dto.ReservationDTO;
import com.example.servicemanagementsystem.enums.ReservationStatus;
import com.example.servicemanagementsystem.enums.ReviewStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    // public ReservationDTO getReservationDTO(){
    //     ReservationDTO dto = new ReservationDTO();
    //     dto.setAdId(id);
    //     dto.setServiceName(ad.getServiceName());
    //     dto.setBookDate(bookDate);
    //     dto.setReservationStatus(reservationStatus);
    //     dto.setReviewStatus(reviewStatus);
    //     dto.setAdId(ad.getId());
    //     dto.setCompanyId(company.getId());
    //     dto.setUserName(user.getName());
    //     return dto;

    // }

    public ReservationDTO getReservationDTO() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(id); // Set the primary key (reservation ID)
        dto.setAdId(ad.getId()); // Set the Ad ID
        dto.setServiceName(ad.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setCompanyId(company.getId());
        dto.setUserId(user.getId()); // Set the User ID
        dto.setUserName(user.getName()); // Set the User Name
        return dto;
    }
}
