package com.example.servicemanagementsystem.dto;



import java.util.Date;

import com.example.servicemanagementsystem.enums.ReservationStatus;
import com.example.servicemanagementsystem.enums.ReviewStatus;

import lombok.Data;

@Data
public class ReservationDTO {
    
    private Long id;
    
    private Date bookDate;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Long userId;

    private String userName;

    private Long companyId;

    private Long adId;

}
