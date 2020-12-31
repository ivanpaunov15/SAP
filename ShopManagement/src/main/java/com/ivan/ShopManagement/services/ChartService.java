package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.SaleRepository;
import com.ivan.ShopManagement.dao.StatisticsRepository;
import com.ivan.ShopManagement.model.Statistics;
import com.ivan.ShopManagement.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartService {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    SaleRepository saleRepository;

    public Map<String, Integer> getStatisticsForAllSales(){
        Map<String, Integer> graphData = new TreeMap<>();
        List<Statistics> pieChartList = statisticsRepository.findAll();
        for(Statistics item:pieChartList){
            graphData.put(item.getProduct(),item.getQuantity());
        }
        return graphData;
    }

    public Map<String, Integer> getStatisticsByNameOfClient(String name){
        Map<String, Integer> graphData = new TreeMap<>();
        List<Sale> saleList = saleRepository.getAllSalesOfClient(name);
        List<Statistics> pieChartList = new ArrayList<>();
        int index = 0;
        int quantity = 0;
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.stream().anyMatch(x->x.getProduct().equals(item.getProduct()))){
                Statistics temp = pieChartList.stream().filter(x->x.getProduct().equals(item.getProduct())).findAny().orElse(null);
                quantity = temp.getQuantity();
                pieChartList.remove(temp);
                item.setQuantity(item.getQuantity()+quantity);
                pieChartList.add(item);
            }
            else{
                pieChartList.add(item);
            }
        }
        for(Statistics item:pieChartList){
            graphData.put(item.getProduct(),item.getQuantity());
        }
        return graphData;
    }

    public Map<String, Integer> getStatisticsByRepresentativeId(long id){
        Map<String, Integer> graphData = new TreeMap<>();
        List<Sale> saleList = saleRepository.getAllSalesByRepresentativeId(id);
        List<Statistics> pieChartList = new ArrayList<>();
        int index = 0;
        int quantity = 0;
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.stream().anyMatch(x->x.getProduct().equals(item.getProduct()))){
                Statistics temp = pieChartList.stream().filter(x->x.getProduct().equals(item.getProduct())).findAny().orElse(null);
                quantity = temp.getQuantity();
                pieChartList.remove(temp);
                item.setQuantity(item.getQuantity()+quantity);
                pieChartList.add(item);
            }
            else{
                pieChartList.add(item);
            }
        }
        for(Statistics item:pieChartList){
            graphData.put(item.getProduct(),item.getQuantity());
        }
        return graphData;
    }

    public Map<String, Integer> getStatisticsForPeriod(String firstDate, String secondDate){
        Map<String, Integer> graphData = new TreeMap<>();
        List<Sale> saleList = saleRepository.getAllSalesBetweenPeriod(firstDate,secondDate);
        List<Statistics> pieChartList = new ArrayList<>();
        int index = 0;
        int quantity = 0;
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.stream().anyMatch(x->x.getProduct().equals(item.getProduct()))){
                Statistics temp = pieChartList.stream().filter(x->x.getProduct().equals(item.getProduct())).findAny().orElse(null);
                quantity = temp.getQuantity();
                pieChartList.remove(temp);
                item.setQuantity(item.getQuantity()+quantity);
                pieChartList.add(item);
            }
            else{
                pieChartList.add(item);
            }
        }
        for(Statistics item:pieChartList){
            graphData.put(item.getProduct(),item.getQuantity());
        }
        return graphData;
    }

    public Map<String, Integer> getStatisticsByNameOfRepresentative(String name){
        Map<String, Integer> graphData = new TreeMap<>();
        List<Sale> saleList = saleRepository.getAllSalesByRepresentativeName(name);
        List<Statistics> pieChartList = new ArrayList<>();
        int index = 0;
        int quantity = 0;
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.stream().anyMatch(x->x.getProduct().equals(item.getProduct()))){
                Statistics temp = pieChartList.stream().filter(x->x.getProduct().equals(item.getProduct())).findAny().orElse(null);
                quantity = temp.getQuantity();
                pieChartList.remove(temp);
                item.setQuantity(item.getQuantity()+quantity);
                pieChartList.add(item);
            }
            else{
                pieChartList.add(item);
            }
        }
        for(Statistics item:pieChartList){
            graphData.put(item.getProduct(),item.getQuantity());
        }
        return graphData;
    }
}
