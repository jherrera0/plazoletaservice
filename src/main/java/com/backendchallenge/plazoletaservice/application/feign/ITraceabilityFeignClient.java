package com.backendchallenge.plazoletaservice.application.feign;

import com.backendchallenge.plazoletaservice.application.http.dto.request.AssignEmployeeToOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.OrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.application.http.dto.request.UpdateOrderTraceabilityRequest;
import com.backendchallenge.plazoletaservice.infrastructure.configuration.feign.FeignClientsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "traceability-service", url = "http://localhost:8083",configuration = FeignClientsConfig.class)
public interface ITraceabilityFeignClient {

    @PostMapping("/traceability/createOrderTraceability")
    void createOrderTraceability(OrderTraceabilityRequest request);

    @PostMapping("/traceability/updateOrderTraceability")
    void updateOrderTraceability(UpdateOrderTraceabilityRequest request);

    @PostMapping("/traceability/assignEmployeeToOrderTraceability")
    void assignEmployeeToOrderTraceability(AssignEmployeeToOrderTraceabilityRequest request);

}
