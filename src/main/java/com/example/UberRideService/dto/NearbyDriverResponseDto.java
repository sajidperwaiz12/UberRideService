package com.example.UberRideService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbyDriverResponseDto {
    private Long driverId;
    private String driverName;
    private String vehicleNumber;
    private String vehicleType;
    private Double rating;
    private Double latitude;
    private Double longitude;
    private Double distanceInKm;
}