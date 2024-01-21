package com.example.order.integration;

import com.example.order.dto.PaymentDto;
import com.example.order.dto.WareHouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PaymentServiceIntegration {

    private final WebClient paymentServiceWebClient;

    public Long getPayment(PaymentDto paymentDto) {
        return paymentServiceWebClient.post()
                .uri("/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new PaymentDto())
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    public Long cancelPayment(Long id) {
        return paymentServiceWebClient.post()
                .uri("/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(id)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }


    public Long commitePayment(Long id) {
        return paymentServiceWebClient.post()
                .uri("/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(id)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

}
