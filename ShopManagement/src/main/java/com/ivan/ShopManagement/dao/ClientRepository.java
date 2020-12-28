package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value="SELECT * FROM shop.clients s WHERE s.id =:id",nativeQuery=true)
    List<Client> findByID(@Param("id") long id);
}
