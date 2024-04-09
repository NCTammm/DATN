package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.OrderDetail;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.model.response.OrderDetailResponse;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class OrderClientRestController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/my-order/order-detail/{id}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable("id") Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = orderDetails
                .stream()
                .map(OrderDetailResponse::formOrderDetail)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailResponses);
    }

}
