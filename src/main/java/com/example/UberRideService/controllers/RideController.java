package com.example.UberRideService.controllers;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.services.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping
    public ResponseEntity<RideResponseDto> createRide(@RequestBody CreateRideRequestDto request) {
        return new ResponseEntity<>(rideService.createRide(request), HttpStatus.CREATED);
    }
}

