package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value="SELECT * FROM shop.sales s WHERE s.id_representative =:idRep",nativeQuery=true)
    public List<Sale> getAllSalesByRepresentativeId(@Param("idRep") long id);

    @Query(value="SELECT * FROM shop.sales s WHERE s.representative =:name",nativeQuery=true)
    public List<Sale> getAllSalesByRepresentativeName(@Param("name") String name);

    @Query(value="SELECT * FROM shop.sales s WHERE s.client =:name",nativeQuery=true)
    public List<Sale> getAllSalesOfClient(@Param("name") String name);

    @Query(value="SELECT * FROM shop.sales s WHERE s.product =:name",nativeQuery=true)
    public List<Sale> getAllSalesOfProduct(@Param("name") String name);

    @Query(value ="SELECT * FROM shop.sales s WHERE s.date >=:firstDate AND s.date <:secondDate",nativeQuery = true)
    public List<Sale> getAllSalesBetweenPeriod(@Param("firstDate")String firstDate, @Param("secondDate") String secondDate);
}
