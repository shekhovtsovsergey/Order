package com.example.order.integration;

import com.example.order.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class DeliveryServiceIntegration {

    private final WebClient deliveryServiceWebClient;

    public String getDelivery() {
        System.out.println("DeliveryServiceIntegration");
        return deliveryServiceWebClient.post()
                .uri("/booking")
                .retrieve()
                .bodyToMono(DeliveryDto.class)
                .block().toString();
    }

}
