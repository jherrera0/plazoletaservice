package com.backendchallenge.plazoletaservice.application.feign;

import com.backendchallenge.plazoletaservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.module.Configuration;

@FeignClient(name = "user-service", url = "http://localhost:8080", configuration= FeignClientsConfig.class)
public interface IFeignUserClient {

    @GetMapping("/user/FindOwnerById")
    boolean findOwnerById(@RequestParam("ownerId") Long ownerId);

    @GetMapping("/user/GetClientPhoneById")
    String getPhoneById(@RequestParam("userId") Long userId);
}
