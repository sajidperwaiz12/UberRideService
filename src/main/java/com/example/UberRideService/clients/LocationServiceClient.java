package com.example.UberRideService.clients;

import com.example.UberRideService.dto.DriverLocationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "UberLocationService", url = "http://localhost:7478")
public interface LocationServiceClient {
    @GetMapping("/api/v1/location/drivers/nearby")
    List<DriverLocationResponseDto> getNearbyDrivers(@RequestParam Double latitude, @RequestParam Double longitude);
}
