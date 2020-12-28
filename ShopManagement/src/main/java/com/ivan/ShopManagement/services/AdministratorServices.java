package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.AdministratorDao;
import com.ivan.ShopManagement.dao.AdministratorDataAccessService;
import com.ivan.ShopManagement.model.Administrator;
import com.ivan.ShopManagement.model.Product;
import com.ivan.ShopManagement.model.Sale;
import com.ivan.ShopManagement.model.SalesRepresentative;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServices {
    @Autowired
    private final AdministratorDao administratorDao;

    @Autowired
    private AdministratorDataAccessService administratorDataAccessService;

    public AdministratorServices( AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    public int addAdministrator(Administrator administrator) {
        return this.administratorDao.insertAdministratorDao(administrator);
    }

    public List<Administrator> getAllAdministrators() {
        return this.administratorDao.selectAllAdministrators();
    }

    public Optional<Administrator> getAdministratorById(long id){
        return administratorDao.selectAdministratorById(id);
    }

    public int addSalesRepresentative(SalesRepresentative salesRepresentative) {
        return this.administratorDao.insertSalesRepresentative(salesRepresentative);
    }

    public List<SalesRepresentative> getAllSalesRepresentative() {
        return this.administratorDao.selectAllSalesRepresentative();
    }

    public int addProduct(Product product) {
        return this.administratorDao.insertProduct(product);
    }

    public List<Product> getAllProduct() {
        return this.administratorDao.selectAllProducts();
    }

    public  Optional<Product> getProductById(long id){
        return administratorDao.selectProductById(id);
    }

    public  int deleteProduct(long id){
        return administratorDao.deleteProduct(id);
    }

    public int updateProduct(long id, Product newProduct){
        return administratorDao.updateProduct(id,newProduct);
    }

    public Optional<SalesRepresentative> getSalesRepresentativeById(long id){
        return administratorDao.selectSalesRepresentativeById(id);
    }

    public int deleteSalesRepresentative(long id){
        return administratorDao.deleteSalesRepresentativeById(id);
    }

    public int updateSalesRepresentative(long id, SalesRepresentative newSalesRepresentative){
        return administratorDao.updateSalesRepresentativeById(id,newSalesRepresentative);
    }

    public List<Sale> getAllSales(){
        return administratorDao.selectAllSales();
    }

    public Optional<Sale> getSaleById(long id){
        return administratorDao.selectSaleById(id);
    }

    public List<Sale> getSalesByRepresentativeId(long id){
        return administratorDataAccessService.selectAllSalesByRepresentativeId(id);
    }
    public List<Sale> getSalesBetweenPeriod(String firstDate, String secondDate){
        return administratorDataAccessService.selectAllSalesBetweenPeriod(firstDate,secondDate);
    }

    public List<Sale> getClientSales(String name){
        return administratorDataAccessService.selectSalesByClient(name);
    }
}
