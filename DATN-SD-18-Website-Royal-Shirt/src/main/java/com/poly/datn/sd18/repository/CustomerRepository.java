package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT [id]\n" +
            "      ,[name]\n" +
            "      ,[phone]\n" +
            "      ,[avatar]\n" +
            "      ,[password]\n" +
            "      ,[status]\n" +
            "      ,[email]\n" +
            "      ,[created_date]\n" +
            "      ,[updated_date]\n" +
            "  FROM [dbo].[customers]\n" +
            "  WHERE [status] = 0",nativeQuery = true)
    List<Customer> getAllActive();

    List<Customer> findByPhone(String phone);
}
