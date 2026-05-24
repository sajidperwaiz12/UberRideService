package com.example.UberRideService.clients;

import com.example.UberRideService.dto.NearbyDriverResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "UberUserService", url = "http://localhost:7476")
public interface UserServiceClient {
    @GetMapping("api/v1/drivers/nearby")
    List<NearbyDriverResponseDto> getNearbyDrivers(@RequestParam Double latitude, @RequestParam Double longitude);
}

