package com.example.UberRideService.mapper;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.entities.Ride;
import com.example.UberRideService.enums.RideStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = RideStatus.class)
public interface RideMapper {
    RideResponseDto toRideResponseDto(Ride ride);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driverId", ignore = true)
    @Mapping(target = "status", expression = "java(RideStatus.REQUESTED)")
    @Mapping(target = "fare", ignore = true)
    @Mapping(target = "otp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Ride toRide(CreateRideRequestDto rideRequestDto);
}

