package com.poly.datn.sd18.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.repository.OrderRepository;

public interface OrderService {
    List<Order> getAll();

    Order getById(int id);

    Order update(Order order);

    List<Order> getByType(boolean type);

    List<Order> getByStatus(int status);

    List<Order> getByStatusAndType(int status, boolean type);

}
