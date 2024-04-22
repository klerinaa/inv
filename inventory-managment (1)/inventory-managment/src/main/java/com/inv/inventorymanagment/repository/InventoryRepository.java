package com.inv.inventorymanagment.repository;

import com.inv.inventorymanagment.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByProductId(Long productId);

    @Query("SELECT i.productId FROM Inventory i WHERE i.availableQuantity > 0")
    List<Long> findAllAvailableProductsIds();
}
