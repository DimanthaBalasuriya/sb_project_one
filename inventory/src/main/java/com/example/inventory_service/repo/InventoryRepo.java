package com.example.inventory_service.repo;

import com.example.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT * FROM Inventory WHERE id=?1", nativeQuery = true)
    Inventory getInventoryById(Integer inventoryID);
}
