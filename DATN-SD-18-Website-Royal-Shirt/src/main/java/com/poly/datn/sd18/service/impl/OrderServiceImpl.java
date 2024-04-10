package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDTO;
import com.poly.datn.sd18.repository.OrderDetailRepository;
import com.poly.datn.sd18.repository.OrderRepository;
import com.poly.datn.sd18.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        Order order = orderDTO.map(new Order());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByCustomerId(Integer customerId) {
        return orderRepository.findOrderByCustomerId(customerId);
    }

    @Override
    public Order setStatusOrderById(Integer orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            if (order.getStatus() == 1) {
                order.setStatus(6);
                order.setCancelDate(new Date(System.currentTimeMillis()));
            }
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order findOrderById(Integer orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public Float calculateTotalPriceByOrderId(Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        float totalPrice = 0.0f;
        for (OrderDetail od : orderDetails) {
            totalPrice += od.getQuantity() * od.getProductDetail().getPrice();
        }
        return totalPrice;
    }

    @Override
    public List<Order> getAllOrderByCustomerId(Integer customerId) {
        return orderRepository.getAllOrderByCustomerId(customerId);
    }
}
