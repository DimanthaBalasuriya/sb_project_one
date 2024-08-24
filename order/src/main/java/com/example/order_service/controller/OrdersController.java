package com.example.order_service.controller;

import com.example.order_service.dto.OrdersDTO;
import com.example.order_service.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/order/")
public class OrdersController {

    @Autowired
    private OrdersService orderService;

    @GetMapping("/getOrders")
    public List<OrdersDTO> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{orderId}")
    public OrdersDTO getOrderById(@PathVariable Integer orderID) {
        return orderService.getOrderById(orderID);
    }

    @PostMapping("/addOrder")
    public OrdersDTO addOrder(@RequestBody OrdersDTO ordersDTO) {
        return orderService.addOrder(ordersDTO);
    }

    @PutMapping("/updateOrder")
    public OrdersDTO updateOrder(@RequestBody OrdersDTO ordersDTO) {
        return orderService.updateOrder(ordersDTO);
    }

    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestBody OrdersDTO ordersDTO) {
        return orderService.deleteOrder(ordersDTO);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable Integer ordersId) {
        return orderService.deleteOrder(ordersId);
    }
}
