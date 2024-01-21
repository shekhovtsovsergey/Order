package com.example.order.service;

import com.example.order.dto.PaymentDto;
import com.example.order.integration.DeliveryServiceIntegration;
import com.example.order.integration.PaymentServiceIntegration;
import com.example.order.integration.WareServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final DeliveryServiceIntegration deliveryServiceIntegration;
    private final WareServiceIntegration wareServiceIntegration;
    private final PaymentServiceIntegration paymentServiceIntegration;


    @Override
    public ResponseEntity<String> createOrder(PaymentDto paymentDto) {
        Long prepareFromDelivery = null;
        Long prepareFromWare = null;
        Long prepareFromDPayment = null;
        try {
            prepareFromDelivery = deliveryServiceIntegration.getDelivery();
            prepareFromWare = wareServiceIntegration.getWare();
            prepareFromDPayment = paymentServiceIntegration.getPayment(paymentDto);
        } catch (Exception e) {
            deliveryServiceIntegration.getDelivery();
            wareServiceIntegration.getWare();
            paymentServiceIntegration.cancelPayment(prepareFromDPayment);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
        deliveryServiceIntegration.getDelivery();
        wareServiceIntegration.getWare();
        paymentServiceIntegration.commitePayment(prepareFromDPayment);
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}