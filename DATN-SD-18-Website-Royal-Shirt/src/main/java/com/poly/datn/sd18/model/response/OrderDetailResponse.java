package com.poly.datn.sd18.model.response;

import com.poly.datn.sd18.entity.OrderDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailResponse {
     private Integer id;

    private Integer quantity;

    private Float price;

    private Integer status;

    private Integer orderId;

    private Integer productDetailId;

    public static OrderDetailResponse formOrderDetail(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getId())
                .productDetailId(orderDetail.getProductDetail().getId())
                .price(orderDetail.getPrice())
                .quantity(orderDetail.getQuantity())
                .status(orderDetail.getStatus())
                .build();
    }
}
