package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Repository
public class AdministratorDataAccessService implements AdministratorDao {

    @Autowired
    private SaleRepresentativeRepository saleRepresentativeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public int insertAdministratorDao(long id, @Valid @RequestBody Administrator administrator) {
        adminRepository.save(administrator);
        return 1;
    }

    public List<Administrator> selectAllAdministrators() {

       return adminRepository.findAll();
    }

    @Override
    public Optional<Administrator> selectAdministratorById(long id) {
      return adminRepository.findById(id);
    }


    @Override
    public int insertSalesRepresentativeDao(long id, @Valid @RequestBody SalesRepresentative salesRepresentative) {
        saleRepresentativeRepository.save(salesRepresentative);
        return 1;
    }

    @Override
    public List<SalesRepresentative> selectAllSalesRepresentative() {
        return saleRepresentativeRepository.findAll();
    }

    @Override
    public Optional<SalesRepresentative> selectSalesRepresentativeById(long id) {
        return saleRepresentativeRepository.findById(id);
    }

    @Override
    public int deleteSalesRepresentativeById(long id) {
        saleRepresentativeRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updateSalesRepresentativeById(long id, SalesRepresentative salesRepresentative) {
       List<SalesRepresentative> list = saleRepresentativeRepository.findByID(id);
       if(list.size()>0){
           List<Sale> saleList = saleRepository.getAllSalesByRepresentativeName(list.get(0).getName());
           for(Sale sale: saleList){
               sale.setId(sale.getId());
               sale.setSalesRepresentative(salesRepresentative.getName());
               saleRepository.save(sale);
           }
           salesRepresentative.setId(id);
           saleRepresentativeRepository.save(salesRepresentative);
           return 1;
       }
       return 0;

    }

    @Override
    public int insertProduct(long id, @Valid @RequestBody Product product) {
        productRepository.save(product);
        return 1;
    }

    @Override
    public List<Product> selectAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public int deleteProduct(long id) {
       productRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updateProduct(long id, Product product) {
        List<Product> list = productRepository.findByID(id);
        if(list.size()>0){
            List<Sale> saleList = saleRepository.getAllSalesOfProduct(list.get(0).getName());
            for(Sale sale: saleList){
                sale.setId(sale.getId());
                sale.setProduct(product.getName());
                saleRepository.save(sale);
            }
            product.setId(id);
            productRepository.save(product);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Sale> selectAllSales() {
        return saleRepository.findAll();
    }
    
    @Override
    public Optional<Sale> selectSaleById(long id){
        return saleRepository.findById(id);
    }

    @Override
    public List<Sale> selectAllSalesByRepresentativeId(long id) {
        return saleRepository.getAllSalesByRepresentativeId(id);
    }

    @Override
    public List<Sale> selectAllSalesBetweenPeriod(String firstDate, String secondDate){
        return saleRepository.getAllSalesBetweenPeriod(firstDate,secondDate);
    }

    @Override
    public List<Sale> selectSalesByClient(String name){
        return saleRepository.getAllSalesOfClient(name);
    }
}

