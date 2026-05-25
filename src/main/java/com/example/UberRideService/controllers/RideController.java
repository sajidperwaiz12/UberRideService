package com.example.UberRideService.controllers;

import com.example.UberRideService.dto.CreateRideRequestDto;
import com.example.UberRideService.dto.RideResponseDto;
import com.example.UberRideService.services.RideService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping
    public ResponseEntity<RideResponseDto> createRide(@RequestBody CreateRideRequestDto request) {
        return new ResponseEntity<>(rideService.createRide(request), HttpStatus.CREATED);
    }

    @PutMapping("/{rideId}/accept")
    public ResponseEntity<RideResponseDto> acceptRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.acceptRide(rideId));
    }

    @PutMapping("/{rideId}/reject")
    public ResponseEntity<RideResponseDto> rejectRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.rejectRide(rideId));
    }

    @PutMapping("/{rideId}/verify-otp")
    public ResponseEntity<RideResponseDto> verifyOtp(@PathVariable Long rideId, @RequestParam Integer otp) {
        return ResponseEntity.ok(rideService.verifyOtp(rideId, otp));
    }

    @PutMapping("/{rideId}/start")
    public ResponseEntity<RideResponseDto> startRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.startRide(rideId));
    }

    @PutMapping("/{rideId}/complete")
    public ResponseEntity<RideResponseDto> completeRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.completeRide(rideId));
    }

    @PutMapping("/{rideId}/cancel")
    public ResponseEntity<RideResponseDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(rideService.cancelRide(rideId));
    }
}

