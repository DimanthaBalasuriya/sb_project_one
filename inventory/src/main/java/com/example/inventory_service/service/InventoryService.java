package com.example.inventory_service.service;

import com.example.inventory_service.dto.InventoryDTO;
import com.example.inventory_service.model.Inventory;
import com.example.inventory_service.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventorys() {
        List<Inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {
        }.getType());
    }

    public InventoryDTO getInventoryById(Integer inventoryId) {
        Inventory inventory = inventoryRepo.getInventoryById(inventoryId);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO addInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteInventory(InventoryDTO inventoryDTO) {
        inventoryRepo.delete(modelMapper.map(inventoryDTO, Inventory.class));
        return "Successfully Deleted";
    }

    public String deleteInventory(Integer inventoryId) {
        inventoryRepo.deleteById(inventoryId);
        return "Successfully Deleted";
    }
}
