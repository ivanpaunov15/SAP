package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Client;
import com.ivan.ShopManagement.model.Sale;
import com.ivan.ShopManagement.model.SalesRepresentative;

import java.util.List;
import java.util.Optional;


public interface SalesRepresentativeDao extends EmployeeDao{
    int insertClientDao(long id, Client client);

    default int insertClientDao(Client client) {
        return this.insertClientDao(client.getId(), client);
    }

    List<Client> selectAllClients();

    Optional<Client> selectClientById(long id);

    int deleteClientById(long id);

    int updateClientById(long id, Client client);

    int sellProduct(String representativeName, long clientId, long productId, int quantity, float discount);

    List<Sale> selectSalesByName(String name);

    List<Sale> selectAllSalesByRepresentativeId(long id);

    List<SalesRepresentative> selectSaleRepresentativeByName(String name);

    int sendMailsToEveryone(String topic, String text);

    int sendMailToClient(String topic, String text, String receiver);
}

