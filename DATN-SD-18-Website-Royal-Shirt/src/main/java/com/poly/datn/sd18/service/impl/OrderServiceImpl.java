package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.repository.OrderRepository;
import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public List<Order> getByType(boolean type) {
        return orderRepository.getByType(type);
    }

    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public List<Order> getByStatus(int status) {
        return orderRepository.getByStatus(status);
    }

    public Order getById(int id) {
        return orderRepository.getById(id);
    }

    public List<Order> getByStatusAndType(int status, boolean type) {
        // Implement the method
        return orderRepository.getByStatusAndType(status, type);
    }

    public Float totalOrders() {
        return orderRepository.totalOrders();
    }

    public Float totalOrdersByMonth(Date date) {
        return orderRepository.totalOrdersByMonth(date);
    }

    public Float totalOrdersByDate(Date date) {
        return orderRepository.totalOrdersByDate(date);
    }

    public Float totalOrdersByYear(Date date) {
        return orderRepository.totalOrdersByYear(date);
    }

    public int countOrders() {
        return orderRepository.countOrders();
    }

    public int countOrdersByMonth(Date date) {
        return orderRepository.countOrdersByMonth(date);
    }

    public int countOrdersByDate(Date date) {
        return orderRepository.countOrdersByDate(date);
    }

    public int countOrdersByYear(Date date) {
        return orderRepository.countOrdersByYear(date);
    }

    @Transactional
    public List<Object> thongkedonhang(int Nam) {
        return orderRepository.thongkedonhang(Nam);
    }

    @Transactional
    public List<Object> thongKeSoSanPham(int Nam) {
        return orderRepository.thongKeSoSanPham(Nam);
    }

}
