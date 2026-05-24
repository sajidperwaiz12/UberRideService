package com.example.UberRideService.mapper;

import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.entities.Ride;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RideMapper {
    RideResponseDto toRideResponseDto(Ride ride);
}

