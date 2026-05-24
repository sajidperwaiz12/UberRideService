package com.example.UberRideService.services;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;

public interface RideService {
    RideResponseDto createRide(CreateRideRequestDto request);
}
