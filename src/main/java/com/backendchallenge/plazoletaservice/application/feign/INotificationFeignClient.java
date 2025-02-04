package com.backendchallenge.plazoletaservice.application.feign;

import com.backendchallenge.plazoletaservice.application.http.dto.request.NotificationRequest;
import com.backendchallenge.plazoletaservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "http://localhost:8082", configuration= FeignClientsConfig.class)
public interface INotificationFeignClient {
    @PostMapping("/notification/send")
    void sendNotification(NotificationRequest notificationRequest);

    @GetMapping("/notification/getPin")
    String findPinByPhoneNumber(String phone);

    @GetMapping("/notification/exist")
    Boolean existPinByPhoneNumber(String phone);
}
