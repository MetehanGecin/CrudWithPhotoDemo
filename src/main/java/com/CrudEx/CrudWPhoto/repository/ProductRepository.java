package com.CrudEx.CrudWPhoto.repository;

import com.CrudEx.CrudWPhoto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.productName, ' ', product.price) LIKE %?1%")
    public List<Product> search(String keyword);
    public Product findByproductName(String productName);
}
