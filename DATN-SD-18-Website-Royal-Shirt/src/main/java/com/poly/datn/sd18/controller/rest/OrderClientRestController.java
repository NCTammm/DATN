package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.model.response.OrderDetailResponse;
import com.poly.datn.sd18.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class OrderClientRestController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/order-detail/{id}")
    public List<OrderDetailResponse> getOrderDetailsByOrderId(@PathVariable("id") Integer orderId) {
        return orderDetailService.findOrderDetailByOrderId(orderId);
    }
}
