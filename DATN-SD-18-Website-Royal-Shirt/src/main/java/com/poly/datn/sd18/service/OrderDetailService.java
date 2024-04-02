package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.OrderDetail;

public interface OrderDetailService {

    OrderDetail getById(int id);

    OrderDetail add(OrderDetail orderDetail, int productId, int orderId);

    OrderDetail deleteOrderDetail(int id);

    OrderDetail PlusAmountOrderDetail(int id, int amount);

    OrderDetail MinusAmountOrderDetail(int id, int amount);

    Order ChangeOrderStatus(int id, int status);
}
