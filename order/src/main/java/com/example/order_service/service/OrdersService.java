package com.example.order_service.service;

import com.example.inventory_service.dto.InventoryDTO;
import com.example.order_service.common.ErrorOrderResponse;
import com.example.order_service.common.OrderResponse;
import com.example.order_service.common.SuccessOrderResponse;
import com.example.order_service.dto.OrdersDTO;
import com.example.order_service.model.Orders;
import com.example.order_service.repo.OrdersRepo;
import com.example.product_service.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrdersService {

    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    @Autowired
    private OrdersRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrdersService(WebClient inventoryWebClient, WebClient productWebClient, OrdersRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<OrdersDTO> getAllOrders() {
        List<Orders> orderList = orderRepo.findAll();
        return modelMapper.map(orderList, new TypeToken<List<OrdersDTO>>() {
        }.getType());
    }

    public OrdersDTO getOrderById(Integer orderId) {
        Orders orders = orderRepo.getOrderById(orderId);
        return modelMapper.map(orders, OrdersDTO.class);
    }

    public OrderResponse addOrder(OrdersDTO ordersDTO) {

        //Internala microservice communication
        Integer itemId = ordersDTO.getItemId();

        //Webclient integration
        try {
            InventoryDTO inventoryResponse = inventoryWebClient.get().uri(uriBuilder -> uriBuilder.path("/inventory/getInventoryById/{itemId}").build(itemId)).retrieve().bodyToMono(InventoryDTO.class).block();

            System.out.println(inventoryResponse);
            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebClient.get().uri(uriBuilder -> uriBuilder.path("/product/getProductById/{productId}").build(productId)).retrieve().bodyToMono(ProductDTO.class).block();
            assert productResponse != null;

            if (inventoryResponse.getQuantity() > 0) {
                if (productResponse.getForSale() == 1) {
                    orderRepo.save(modelMapper.map(ordersDTO, Orders.class));
                    return new SuccessOrderResponse(ordersDTO);
                } else {
                    return new ErrorOrderResponse("This item is not available to sale");
                }
            } else {
                return new ErrorOrderResponse("This item not available");
            }

        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("This item not available");
            }
        }
        return null;
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
