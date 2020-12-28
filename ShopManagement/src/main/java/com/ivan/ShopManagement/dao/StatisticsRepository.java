package com.ivan.ShopManagement.dao;


import com.ivan.ShopManagement.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query(value="SELECT * FROM shop.statistics s WHERE s.product =:product",nativeQuery=true)
    Statistics findByProduct(@Param("product") String product);
}
