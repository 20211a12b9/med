package com.vms.medxbid.controllers;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vms.medxbid.models.BidRequest;
import com.vms.medxbid.models.Product;
import com.vms.medxbid.models.ProductCategory;
import com.vms.medxbid.services.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {
   
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    

    @PostMapping("/productSearch")
    public List<Product> searchAllProducts(@RequestBody String keyword) {
       
        System.out.println("Keyword: " + keyword);
        return productService.searchAllProducts(keyword);
    }

    @GetMapping("/productcategories")
    public List<ProductCategory> getProductCategories() {
       
    
        return productService.getAllProductCategories();
    }

    @GetMapping("/nproductsforcategory/{categoryId}")
    public List<Product> getNProductsForCategory(@PathVariable Long categoryId) {
        return productService.getNProductsForCategory(categoryId);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
}
