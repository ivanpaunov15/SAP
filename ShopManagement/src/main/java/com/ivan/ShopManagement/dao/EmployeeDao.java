package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Product;
import com.ivan.ShopManagement.model.SalesRepresentative;

import java.util.List;
import java.util.Optional;


public interface EmployeeDao {
    Optional<Product> selectProductById(long id);

    List<Product> selectAllProducts();

    Optional<SalesRepresentative> selectSalesRepresentativeById(long id);
}
