package com.inv.inventorymanagment.service;

import com.inv.inventorymanagment.entity.Inventory;
import com.inv.inventorymanagment.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void deleteInventoryByProductId(Long productId) {
        inventoryRepository.deleteByProductId(productId);
    }
    public List<Long> getAllAvailableProductIds() {
        return inventoryRepository.findAllAvailableProductsIds();
    }

}
