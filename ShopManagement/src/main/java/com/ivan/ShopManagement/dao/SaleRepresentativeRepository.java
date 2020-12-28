package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.SalesRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleRepresentativeRepository extends JpaRepository<SalesRepresentative, Long> {
    @Query(value="SELECT * FROM shop.representatives s WHERE s.name =:name",nativeQuery=true)
    List<SalesRepresentative> findByName(@Param("name") String name);

    @Query(value="SELECT * FROM shop.representatives s WHERE s.id =:id",nativeQuery=true)
    List<SalesRepresentative> findByID(@Param("id") long id);
}
