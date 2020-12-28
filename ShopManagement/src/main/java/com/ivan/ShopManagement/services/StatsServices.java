package com.ivan.ShopManagement.services;

import com.ivan.ShopManagement.model.Statistics;
import com.ivan.ShopManagement.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class StatsServices {

    @Autowired
    ChartService chartService;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public double getAllMoney(List<Sale> saleList){

        double sum = 0;

        for(Sale sale: saleList){
            sum += (sale.getPrice()*sale.getQuantity());
        }

        return Double.parseDouble(df.format(sum));
    }

    public Sale bestSale(List<Sale> saleList){
       long id = -1;
       double max = -1;
       Sale bestSale = null;
       for(Sale sale: saleList){
           if((sale.getQuantity()*sale.getPrice())>max){
               max = sale.getQuantity()*sale.getPrice();
               bestSale = sale;
           }
       }
       bestSale.setPrice(Double.parseDouble(df.format(bestSale.getPrice())));
       return bestSale;
    }
}
