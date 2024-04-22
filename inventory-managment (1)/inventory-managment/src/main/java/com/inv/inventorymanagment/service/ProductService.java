package com.inv.inventorymanagment.service;

import com.inv.inventorymanagment.entity.Product;
import com.inv.inventorymanagment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    //metodta per te krijuar objekte ne springboot
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByName(String name){
        return productRepository.findByName(name);
    }
    public Product createProduct(Product product){
        return  productRepository.save(product);
    }
    public Product updateProduct(Long id, Product product){
        if(productRepository.existsById(id)){
            product.setId(id);
            return  productRepository.save(product);
        }else{
            return  null;
        }

    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }



}
