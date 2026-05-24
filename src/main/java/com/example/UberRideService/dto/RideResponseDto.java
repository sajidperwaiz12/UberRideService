package com.example.UberRideService.dto;

import com.example.UberRideService.enums.RideStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideResponseDto {
    private Long id;
    private Long passengerId;
    private Long driverId;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double dropOffLatitude;
    private Double dropOffLongitude;
    private RideStatus status;
    private Double fare;
    private Integer otp;
}

