package com.example.UberRideService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverLocationResponseDto {
    private String driverId;
    private String latitude;
    private String longitude;
}

