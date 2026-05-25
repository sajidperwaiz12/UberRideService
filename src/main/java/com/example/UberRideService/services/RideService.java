package com.example.UberRideService.services;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;

public interface RideService {
    RideResponseDto createRide(CreateRideRequestDto request);

    RideResponseDto acceptRide(Long rideId);

    RideResponseDto rejectRide(Long rideId);

    RideResponseDto verifyOtp(Long rideId, Integer otp);

    RideResponseDto startRide(Long rideId);

    RideResponseDto completeRide(Long rideId);

    RideResponseDto cancelRide(Long rideId);
}
