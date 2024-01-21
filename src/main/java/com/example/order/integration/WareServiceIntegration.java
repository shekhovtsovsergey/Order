package com.example.order.integration;

import com.example.order.dto.WareHouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WareServiceIntegration {

    private final WebClient wareServiceWebClient;

    public Long getWare() {
        System.out.println("WareServiceIntegration");
        return wareServiceWebClient.post()
                .uri("/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new WareHouseDto())
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

}
