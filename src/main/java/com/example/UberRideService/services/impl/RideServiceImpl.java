package com.example.UberRideService.services.impl;

import com.example.UberRideService.clients.UserServiceClient;
import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.NearbyDriverResponseDto;
import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.entities.Ride;
import com.example.UberRideService.enums.RideStatus;
import com.example.UberRideService.exceptions.BadRequestException;
import com.example.UberRideService.exceptions.ResourceNotFoundException;
import com.example.UberRideService.mapper.RideMapper;
import com.example.UberRideService.repositories.RideRepository;
import com.example.UberRideService.services.RideService;
import com.example.UberRideService.utils.DistanceUtil;
import com.example.UberRideService.utils.FareUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideMapper rideMapper;
    private final UserServiceClient userServiceClient;

    @Override
    public RideResponseDto createRide(CreateRideRequestDto request) {
        validateCoordinates(request);

        List<NearbyDriverResponseDto> nearbyDrivers = userServiceClient.getNearbyDrivers(request.getPickupLatitude(), request.getPickupLongitude());

        if (nearbyDrivers.isEmpty()) {
            throw new BadRequestException("No nearby drivers available");
        }

        NearbyDriverResponseDto assignedDriver = nearbyDrivers.get(0);

        Ride ride = rideMapper.toRide(request);
        ride.setOtp(generateOtp());

        Double rideDistance = DistanceUtil.calculateDistance(
                request.getPickupLatitude(),
                request.getPickupLongitude(),
                request.getDropLatitude(),
                request.getDropLongitude()
        );

        double fare = FareUtil.calculateFare(rideDistance);

        ride.setFare(fare);
        ride.setDriverId(assignedDriver.getDriverId());
        ride.setStatus(RideStatus.DRIVER_ASSIGNED);

        userServiceClient.updateDriverAvailability(assignedDriver.getDriverId(), Boolean.FALSE);

        Ride savedRide = rideRepository.save(ride);
        return rideMapper.toRideResponseDto(savedRide);
    }

    @Override
    public RideResponseDto acceptRide(Long rideId) {
        Ride ride = getRide(rideId);
        ride.setStatus(RideStatus.ACCEPTED);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    @Override
    public RideResponseDto rejectRide(Long rideId) {
        Ride ride = getRide(rideId);
        userServiceClient.updateDriverAvailability(ride.getDriverId(), Boolean.TRUE);
        ride.setDriverId(null);
        ride.setStatus(RideStatus.REQUESTED);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    @Override
    public RideResponseDto verifyOtp(Long rideId, Integer otp) {
        Ride ride = getRide(rideId);

        if (!ride.getOtp().equals(otp)) {
            throw new BadRequestException("Invalid OTP");
        }

        ride.setStatus(RideStatus.OTP_VERIFIED);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    @Override
    public RideResponseDto startRide(Long rideId) {
        Ride ride = getRide(rideId);
        ride.setStatus(RideStatus.IN_PROGRESS);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    @Override
    public RideResponseDto completeRide(Long rideId) {
        Ride ride = getRide(rideId);
        ride.setStatus(RideStatus.COMPLETED);
        userServiceClient.updateDriverAvailability(ride.getDriverId(), Boolean.TRUE);
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    @Override
    public RideResponseDto cancelRide(Long rideId) {
        Ride ride = getRide(rideId);
        ride.setStatus(RideStatus.CANCELLED);

        if (ride.getDriverId() != null) {
            userServiceClient.updateDriverAvailability(ride.getDriverId(), Boolean.TRUE);
        }
        return rideMapper.toRideResponseDto(rideRepository.save(ride));
    }

    private Ride getRide(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride not found"));
    }

    private Integer generateOtp() {
        return 1000 + new Random().nextInt(9000);
    }

    private void validateCoordinates(CreateRideRequestDto request) {
        if (request.getPickupLatitude() == null || request.getPickupLongitude() == null || request.getDropLatitude() == null || request.getDropLongitude() == null) {
            throw new BadRequestException("Coordinates cannot be null");
        }
    }
}

