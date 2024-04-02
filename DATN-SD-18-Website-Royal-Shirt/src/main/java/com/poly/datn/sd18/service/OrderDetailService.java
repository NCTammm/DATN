package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.model.dto.OrderDetailDTO;
import com.poly.datn.sd18.model.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    OrderDetail addOrderDetail(OrderDetailDTO orderDetailDTO);
    List<OrderDetailResponse> findOrderDetailByOrderId(Integer orderId);
    List<OrderDetail> findByOrderId(Integer id);
}
