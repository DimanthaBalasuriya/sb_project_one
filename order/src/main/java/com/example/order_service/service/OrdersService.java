package com.example.order_service.service;

import com.example.order_service.dto.OrdersDTO;
import com.example.order_service.model.Orders;
import com.example.order_service.repo.OrdersRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    private OrdersRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrdersDTO> getAllOrders() {
        List<Orders> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrdersDTO>>() {
        }.getType());
    }

    public OrdersDTO getOrderById(Integer orderId) {
        Orders orders = orderRepo.getOrderById(orderId);
        return modelMapper.map(orders, OrdersDTO.class);
    }

    public OrdersDTO addOrder(OrdersDTO ordersDTO) {
        orderRepo.save(modelMapper.map(ordersDTO, Orders.class));
        return ordersDTO;
    }

    public OrdersDTO updateOrder(OrdersDTO ordersDTO) {
        orderRepo.save(modelMapper.map(ordersDTO, Orders.class));
        return ordersDTO;
    }

    public String deleteOrder(OrdersDTO ordersDTO) {
        orderRepo.delete(modelMapper.map(ordersDTO, Orders.class));
        return "Successfully Deleted";
    }

    public String deleteOrder(Integer ordersId) {
        orderRepo.deleteById(ordersId);
        return "Successfully Deleted";
    }
}
