package com.example.UberRideService.services.impl;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.entities.Ride;
import com.example.UberRideService.exceptions.BadRequestException;
import com.example.UberRideService.mapper.RideMapper;
import com.example.UberRideService.repositories.RideRepository;
import com.example.UberRideService.services.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideMapper rideMapper;

    @Override
    public RideResponseDto createRide(CreateRideRequestDto request) {
        validateCoordinates(request);

        Ride ride = rideMapper.toRide(request);
        ride.setOtp(generateOtp());
        ride.setFare(calculateDummyFare());

        Ride savedRide = rideRepository.save(ride);
        return rideMapper.toRideResponseDto(savedRide);
    }

    private Double calculateDummyFare() {
        return 250.0;
    }

    private Integer generateOtp() {
        return 1000 + new Random().nextInt(9000);
    }

    private void validateCoordinates(CreateRideRequestDto request) {
        if (request.getPickupLatitude() == null || request.getPickupLongitude() == null || request.getDropOffLatitude() == null || request.getDropOffLongitude() == null) {
            throw new BadRequestException("Coordinates cannot be null");
        }
    }
}

