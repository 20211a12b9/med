package com.vms.medxbid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.vms.medxbid.models.Product;
import com.vms.medxbid.models.ProductCategory;
import com.vms.medxbid.repositories.ProductCategoryRepository;
import com.vms.medxbid.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository ProductCategoryRepository;

    @Value("${product.limit}")
    private int productLimit;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductCategory> getAllProductCategories() {
        return ProductCategoryRepository.findAll();
    }


    public List<Product> getNProductsForCategory(Long categoryId) {
    List<Product> allCategoryProducts = new ArrayList<>();
    List<Product> products = new ArrayList<>();
      List<ProductCategory> productCategories = ProductCategoryRepository.findAll();
      for(ProductCategory pc : productCategories){      
        if(pc.getId() == categoryId) {
            System.out.println(pc.getId() +":"+ categoryId);
             products = productRepository.findFirst10ByProductCategoryOrderByProductId(pc);
             allCategoryProducts.addAll(products);
             break;
        }
       
      }
      return allCategoryProducts;
    }
     
    

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            
            product.get().setProductName(productDetails.getProductName());
            product.get().setProductDesc(productDetails.getProductDesc());
            product.get().setProductManu(productDetails.getProductManu());
            product.get().setProductCategory(productDetails.getProductCategory());
            return productRepository.save(product.get());
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchAllProducts(String keyword) {
         return productRepository.findByProductNameIgnoreCaseLikeOrProductDescIgnoreCaseLike("%"+keyword.toUpperCase()+"%","%"+keyword.toUpperCase()+"%");
          
        }
}