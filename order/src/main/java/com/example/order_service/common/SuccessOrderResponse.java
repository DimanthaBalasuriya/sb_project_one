package com.example.order_service.common;

import com.example.order_service.dto.OrdersDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {

    @JsonUnwrapped
    private final OrdersDTO ordersDTO;

    public SuccessOrderResponse(OrdersDTO ordersDTO) {
        this.ordersDTO = ordersDTO;
    }

}
