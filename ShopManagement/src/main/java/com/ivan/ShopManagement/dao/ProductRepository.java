package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="SELECT * FROM shop.products s WHERE s.id =:id",nativeQuery=true)
    List<Product> findByID(@Param("id") long id);
}
