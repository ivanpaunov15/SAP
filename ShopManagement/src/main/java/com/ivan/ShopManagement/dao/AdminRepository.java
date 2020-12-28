package com.ivan.ShopManagement.dao;

import com.ivan.ShopManagement.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Administrator, Long> {

    @Query(value="SELECT * FROM shop.admins a WHERE a.admin =:name",nativeQuery=true)
    List<Administrator> findByName(@Param("name") String name);
}
