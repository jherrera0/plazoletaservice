package com.backendchallenge.plazoletaservice.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface IFeignUserClient {

    @GetMapping("/user/FindOwnerById")
    boolean findOwnerById(@RequestParam("ownerId") Long ownerId);
}
