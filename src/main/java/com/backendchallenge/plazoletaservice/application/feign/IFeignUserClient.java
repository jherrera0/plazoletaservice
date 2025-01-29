package com.backendchallenge.plazoletaservice.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8080",configuration = FeignClientsConfiguration.class)
public interface IFeignUserClient {
    @GetMapping("/users/findOwnerById")
    boolean findOwnerById(Long ownerId);
}
