package com.example.order_service.repo;

import com.example.order_service.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * FROM Order WHERE id=?1", nativeQuery = true)
    Orders getOrderById(Integer orderID);

}
