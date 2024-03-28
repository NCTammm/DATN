package com.poly.datn.sd18.model.response;

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
}
