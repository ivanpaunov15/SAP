package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Administrator;
import com.ivan.ShopManagement.model.Product;
import com.ivan.ShopManagement.model.Sale;
import com.ivan.ShopManagement.model.SalesRepresentative;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface AdministratorDao extends EmployeeDao{

    int insertAdministratorDao(long id, Administrator administrator);

    default int insertAdministratorDao(Administrator administrator) {
        return this.insertAdministratorDao(administrator.getId(),administrator);
    }

    List<Administrator> selectAllAdministrators();

    Optional<Administrator> selectAdministratorById(long id);

    int insertSalesRepresentativeDao(long id, SalesRepresentative salesRepresentative);

    default int insertSalesRepresentative(SalesRepresentative salesRepresentative) {
        return this.insertSalesRepresentativeDao(salesRepresentative.getId(),salesRepresentative);
    }

    List<SalesRepresentative> selectAllSalesRepresentative();

    int deleteSalesRepresentativeById(long id);

    int updateSalesRepresentativeById(long id, SalesRepresentative salesRepresentative);

    int insertProduct(long id, Product product);

    default int insertProduct(Product product) {
        return this.insertProduct(product.getId(), product);
    }

    int deleteProduct(long id);

    int updateProduct(long id, Product product);

    List<Sale> selectAllSales();

    Optional<Sale> selectSaleById(long id);

    List<Sale> selectAllSalesByRepresentativeId(long id);

    List<Sale> selectAllSalesBetweenPeriod(String firstDate, String secondDate);

    List<Sale> selectSalesByClient(String name);
}

