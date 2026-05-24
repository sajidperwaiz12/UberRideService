package com.example.UberRideService.utils;

public class FareUtil {
    private static final Double BASE_FARE = 50.0;
    private static final Double PER_KM_RATE = 12.0;
    public static Double calculateFare(Double distanceInKm) {
        return BASE_FARE + distanceInKm * PER_KM_RATE;
    }
}

