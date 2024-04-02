package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDetailDTO;
import com.poly.datn.sd18.model.response.OrderDetailResponse;
import com.poly.datn.sd18.repository.OrderDetailRepository;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail addOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailDTO.map(new OrderDetail());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailResponse> findOrderDetailByOrderId(Integer orderId) {
        return orderDetailRepository.findOrderDetailByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> findByOrderId(Integer id) {
        return orderDetailRepository.findByOrderId(id);
    }
}
