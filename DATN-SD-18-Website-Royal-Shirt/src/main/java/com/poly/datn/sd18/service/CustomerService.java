package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllActive();

    Customer add(Customer customer);

    List<Customer> findByPhone(String phone);

    Customer findById(Integer id);
}
