package com.example.UberRideService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRideRequestDto {
    @NotNull
    private Long passengerId;

    @NotNull
    private Double pickupLatitude;

    @NotNull
    private Double pickupLongitude;

    @NotNull
    private Double dropLatitude;

    @NotNull
    private Double dropLongitude;
}

