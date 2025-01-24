package com.cts.dto;



import java.util.Date;

import com.cts.enums.ReservationStatus;
import com.cts.enums.ReviewStatus;

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
