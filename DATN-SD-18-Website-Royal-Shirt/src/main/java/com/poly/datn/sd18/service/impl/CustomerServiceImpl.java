package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.repository.CustomerRepository;
import com.poly.datn.sd18.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllActive() {
        return customerRepository.getAllActive();
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }
}
