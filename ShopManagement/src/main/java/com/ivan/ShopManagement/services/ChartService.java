package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.dao.SaleRepository;
import com.ivan.ShopManagement.dao.StatisticsRepository;
import com.ivan.ShopManagement.model.Statistics;
import com.ivan.ShopManagement.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.contains(item)){
                index = pieChartList.indexOf(item);
                pieChartList.get(index).setQuantity(pieChartList.get(index).getQuantity()+sale.getQuantity());
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
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.contains(item)){
                index = pieChartList.indexOf(item);
                pieChartList.get(index).setQuantity(pieChartList.get(index).getQuantity()+sale.getQuantity());
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
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.contains(item)){
                index = pieChartList.indexOf(item);
                pieChartList.get(index).setQuantity(pieChartList.get(index).getQuantity()+sale.getQuantity());
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
        for(Sale sale:saleList){
            Statistics item = new Statistics(sale.getProduct(),sale.getQuantity());
            if(pieChartList.contains(item)){
                index = pieChartList.indexOf(item);
                pieChartList.get(index).setQuantity(pieChartList.get(index).getQuantity()+sale.getQuantity());
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
