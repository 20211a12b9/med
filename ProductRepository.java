package com.vms.medxbid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vms.medxbid.models.Product;
import com.vms.medxbid.models.ProductCategory;

import jakarta.persistence.Tuple;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findFirst10ByProductCategoryOrderByProductId(ProductCategory productCategory);

        //@Query(value = "select p from Product as pwhere productName like '%:productName%'")
    List<Product> findByProductNameIgnoreCaseLikeOrProductDescIgnoreCaseLike(String productName,String productDesc);
}