package com.inv.inventorymanagment.controllers;

import com.inv.inventorymanagment.entity.Inventory;
import com.inv.inventorymanagment.entity.Product;
import com.inv.inventorymanagment.service.InventoryService;
import com.inv.inventorymanagment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/byname/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);

        // Create an entry in the inventory for the new product
        Inventory inventory = new Inventory();
        inventory.setProductId(savedProduct.getId());
        inventory.setAvailableQuantity(0); // Initial quantity is 0
        inventoryService.updateInventory(inventory);

        return savedProduct;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        // Remove the corresponding entry from the inventory
        inventoryService.deleteInventoryByProductId(id);
    }

    @GetMapping("/inventory")
    public List<Product> getAllAvailableProductsInInventory() {
        List<Long> availableProductIds = inventoryService.getAllAvailableProductIds();

        // Retrieve products with available inventory
        return productService.getAllProducts().stream()
                .filter(product -> availableProductIds.contains(product.getId()))
                .toList();
    }
}
