package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Customer findByEmail(String email);
    @Query(value = """
                SELECT c.*
                FROM customers c
                WHERE
                    c.email =:email
                    and c.password =:password
                    and c.status = 0
            """, nativeQuery = true)
    Customer loginCustomer(@Param("email") String email,
                           @Param("password") String password);
}
