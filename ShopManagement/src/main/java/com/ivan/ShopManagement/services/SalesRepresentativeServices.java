package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.SalesRepresentativeDao;
import com.ivan.ShopManagement.dao.SalesRepresentativeDataAccessService;
import com.ivan.ShopManagement.model.Client;

import java.util.List;
import java.util.Optional;


import com.ivan.ShopManagement.model.Product;
import com.ivan.ShopManagement.model.Sale;
import com.ivan.ShopManagement.model.SalesRepresentative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRepresentativeServices {
    @Autowired
    private final SalesRepresentativeDao salesRepresentativeDao;

    @Autowired
    private SalesRepresentativeDataAccessService salesRepresentativeDataAccessService;

    public SalesRepresentativeServices( SalesRepresentativeDao salesRepresentativeDao) {
        this.salesRepresentativeDao = salesRepresentativeDao;
    }

    public int addClient(Client client) {
        return this.salesRepresentativeDao.insertClientDao(client);
    }

    public Optional<Client> getClientById(long id){
        return salesRepresentativeDao.selectClientById(id);
    }

    public int deleteClient(long id){
        return salesRepresentativeDao.deleteClientById(id);
    }

    public int updateClient(long id, Client newClient){
        return salesRepresentativeDao.updateClientById(id,newClient);
    }

    public List<Client> getAllClients() {
        return this.salesRepresentativeDao.selectAllClients();
    }

    public int sellProduct(String representativeName, long clientId, long productId, int quantity, float discount){
        return salesRepresentativeDao.sellProduct(representativeName,clientId,productId,quantity,discount);
    }

    public List<Product> getAllProducts(){
        return this.salesRepresentativeDao.selectAllProducts();
    }

    public Optional<Product> getProductById(long id){
        return this.salesRepresentativeDao.selectProductById(id);
    }

    public List<Sale> getSalesByRepresentativeId(long id){
        return salesRepresentativeDataAccessService.selectAllSalesByRepresentativeId(id);
    }

    public List<SalesRepresentative> getRepresentativeByName(String name){
        return salesRepresentativeDataAccessService.selectSaleRepresentativeByName(name);
    }

    public List<Sale> getAllSalesByName(String name){
        return salesRepresentativeDataAccessService.selectSalesByName(name);
    }

    public void sendMailsToEveryone(String topic, String text){
        salesRepresentativeDataAccessService.sendMailsToEveryone(topic, text);
    }

    public void sendMailToClient(String mail, String topic, String text){
        salesRepresentativeDataAccessService.sendMailToClient(mail,topic,text);
    }
}

