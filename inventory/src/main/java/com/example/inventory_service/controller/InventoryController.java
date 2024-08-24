package com.example.inventory_service.controller;

import com.example.inventory_service.dto.InventoryDTO;
import com.example.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getInventorys")
    public List<InventoryDTO> getInventorys() {
        return inventoryService.getAllInventorys();
    }

    @GetMapping("/getInventoryById/{inventoryId}")
    public InventoryDTO getInventoryById(@PathVariable Integer inventoryId) {
        return inventoryService.getInventoryById(inventoryId);
    }

    @PostMapping("/addInventory")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventory(inventoryDTO);
    }

    @PutMapping("/updateInventory")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping("/deleteInventory")
    public String deleteInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.deleteInventory(inventoryDTO);
    }

    @DeleteMapping("deleteInventory/{inventoryId}")
    public String deleteInventory(@PathVariable Integer inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }

}
