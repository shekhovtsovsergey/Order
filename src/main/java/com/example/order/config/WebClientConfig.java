package com.example.order.config;

import com.example.order.property.DeliveryServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(
        {
                DeliveryServiceIntegrationProperties.class
        }
)
@RequiredArgsConstructor
public class WebClientConfig {

       private final DeliveryServiceIntegrationProperties deliveryServiceIntegrationProperties;

        @Bean
        public WebClient pictureServiceWebClient() {
            TcpClient tcpClient = TcpClient
                    .create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, deliveryServiceIntegrationProperties.getConnectTimeout())
                    .doOnConnected(connection -> {
                        connection.addHandlerLast(new ReadTimeoutHandler(deliveryServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                        connection.addHandlerLast(new WriteTimeoutHandler(deliveryServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                    });

            return WebClient
                    .builder()
                    .baseUrl(deliveryServiceIntegrationProperties.getUrl())
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .build();
        }

}
