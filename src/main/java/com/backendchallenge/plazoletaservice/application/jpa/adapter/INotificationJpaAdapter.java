package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.INotificationFeignClient;
import com.backendchallenge.plazoletaservice.application.http.dto.request.NotificationRequest;
import com.backendchallenge.plazoletaservice.domain.spi.INotificationPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class INotificationJpaAdapter implements INotificationPersistencePort {
    private final INotificationFeignClient notificationFeignClient;
    @Override
    public void sendNotification(String phoneNumber, Long idOrder) {
        notificationFeignClient.sendNotification(new NotificationRequest(idOrder,phoneNumber));
    }
}
