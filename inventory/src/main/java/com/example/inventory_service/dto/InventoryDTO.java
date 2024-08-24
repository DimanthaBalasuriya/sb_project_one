package com.example.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryDTO {

    private int id;
    private int itemID;
    private int productId;
    private int quantity;

}
