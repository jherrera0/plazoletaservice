package com.backendchallenge.plazoletaservice.domain.spi;

public interface INotificationPersistencePort {
    void sendNotification(String phoneNumber, Long idOrder);

    Boolean existPinByPhoneNumber(String phone);

    String findPinByPhoneNumber(String phone);
}
