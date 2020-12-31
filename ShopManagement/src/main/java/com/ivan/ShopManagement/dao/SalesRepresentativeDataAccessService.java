package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.exceptions.NotEnoughProductsException;
import com.ivan.ShopManagement.model.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

import com.ivan.ShopManagement.services.MailService;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SalesRepresentativeDataAccessService implements SalesRepresentativeDao {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SaleRepresentativeRepository saleRepresentativeRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private MailService mailService;

    @Override
    public int insertClientDao(long id, Client client) {
        clientRepository.save(new Client(client.getName(),client.getMail()));
        return 1;
    }

    @Override
    public List<Client> selectAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> selectClientById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public int deleteClientById(long id) {
        clientRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updateClientById(long id, Client client) {
        List<Client> list = clientRepository.findByID(id);
        if(list.size()>0){
            List<Sale> saleList = saleRepository.getAllSalesOfClient(list.get(0).getName());
            for(Sale sale: saleList){
                sale.setId(sale.getId());
                sale.setClient(client.getName());
                saleRepository.save(sale);
            }
            client.setId(id);
            clientRepository.save(client);
            return 1;
        }
        return 0;
    }

    @Override
    public int sellProduct(String representativeName, long clientId, long productId, int quantity, float discount) {
        Client client = selectClientById(clientId).orElse(null);
        Product product = selectProductById(productId).orElse(null);
        SalesRepresentative salesRepresentative = selectSaleRepresentativeByName(representativeName).get(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Date now = new Date();

        if(product.getQuantity()-quantity>=0){
            product.setId(productId);
            product.setQuantity(product.getQuantity()-quantity);

            Statistics pieChart = statisticsRepository.findByProduct(product.getName());
            if(pieChart != null){
                pieChart.setId(pieChart.getId());
                pieChart.setQuantity(pieChart.getQuantity()+quantity);
                statisticsRepository.save(pieChart);
            }
            else {
                statisticsRepository.save(new Statistics(product.getName(),quantity));
            }
            if(product.getQuantity()<10){
                List<Administrator> administrators = adminRepository.findAll();
                for(Administrator administrator:administrators){
                    mailService.sendEmail(administrator.getEmail(),"Low quantity","Low quantity of " + product.getName() + ". Only " + product.getQuantity()+" left in stock");
                }
                mailService.sendEmail(salesRepresentative.getEmail(),"Low quantity","Low quantity of " + product.getName() + ". Only " + product.getQuantity()+" left in stock");

            }
            productRepository.save(product);
            double price = product.getPrice() - (product.getPrice()*(discount*0.01));
            saleRepository.save(new Sale(salesRepresentative.getId(),salesRepresentative.getName(),client.getName(),product.getName(),price,quantity,now));
            return 1;
        }
        else{
            throw new NotEnoughProductsException("Not enough " + product.getName());
        }
    }

    @Override
    public List<Sale> selectSalesByName(String name) {
        return saleRepository.getAllSalesByRepresentativeName(name);
    }

    @Override
    public Optional<Product> selectProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> selectAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<SalesRepresentative> selectSalesRepresentativeById(long id) {
        return saleRepresentativeRepository.findById(id);
    }

    @Override
    public List<Sale> selectAllSalesByRepresentativeId(long id) {
        return saleRepository.getAllSalesByRepresentativeId(id);
    }

    @Override
    public List<SalesRepresentative> selectSaleRepresentativeByName(String name) {
        return saleRepresentativeRepository.findByName(name);
    }

    @Override
    public int sendMailsToEveryone(String topic, String text){
       List<Client> clients = clientRepository.findAll();

       for(Client client:clients){
           mailService.sendEmail(topic, text, client.getMail());
       }
        return 1;
    }

    @Override
    public int sendMailToClient(String receiver,String topic, String text) {
        mailService.sendEmail(receiver,topic,text);

        return 1;
    }
}

