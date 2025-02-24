package com.backendchallenge.plazoletaservice.application.jpa.adapter;

import com.backendchallenge.plazoletaservice.application.feign.INotificationFeignClient;
import com.backendchallenge.plazoletaservice.application.http.dto.request.NotificationRequest;
import com.backendchallenge.plazoletaservice.domain.until.ConstTest;
import com.backendchallenge.plazoletaservice.domain.until.ConstValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class NotificationJpaAdapterTest {
    @Mock
    private INotificationFeignClient notificationFeignClient;

    @InjectMocks
    private NotificationJpaAdapter notificationJpaAdapter;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @Test
    void sendNotification_withValidRequest() {
        NotificationRequest request = new NotificationRequest(ConstTest.ID_TEST, ConstTest.PHONE_TEST);
        doNothing().when(notificationFeignClient).sendNotification(request);

        notificationJpaAdapter.sendNotification(ConstTest.PHONE_TEST, ConstTest.ID_TEST);

        verify(notificationFeignClient, times(ConstValidation.ONE)).sendNotification(request);
    }
    @Test
    void existPinByPhoneNumber_withValidPhoneNumber_returnsTrue() {
        when(notificationFeignClient.existPinByPhoneNumber(ConstTest.PHONE_TEST)).thenReturn(true);

        boolean result = notificationJpaAdapter.existPinByPhoneNumber(ConstTest.PHONE_TEST);

        assertTrue(result);
        verify(notificationFeignClient, times(ConstValidation.ONE)).existPinByPhoneNumber(ConstTest.PHONE_TEST);
    }
    @Test
    void findPinByPhoneNumber_withValidPhoneNumber_returnsPin() {
        when(notificationFeignClient.findPinByPhoneNumber(ConstTest.PHONE_TEST)).thenReturn(ConstTest.PIN_TEST);

        String result = notificationJpaAdapter.findPinByPhoneNumber(ConstTest.PHONE_TEST);

        assertEquals(ConstTest.PIN_TEST, result);
        verify(notificationFeignClient, times(ConstValidation.ONE)).findPinByPhoneNumber(ConstTest.PHONE_TEST);
    }
}