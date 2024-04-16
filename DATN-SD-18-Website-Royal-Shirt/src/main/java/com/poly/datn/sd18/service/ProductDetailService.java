package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.CartDetail;
import com.poly.datn.sd18.entity.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAllProductDetail();
    ProductDetail getProductDetailByProductId(Integer productId);
    ProductDetail findByProductIdAndColorIdAndSizeId(Integer productId, Integer colorId, Integer sizeId);
    ProductDetail findProductDetailById(Integer id);
    List<ProductDetail> findProductDetailIdByCartDetailId(List<Integer> cartDetailId);

    List<ProductDetail> getProductDetailsByIds(List<Integer> id);

    ProductDetail showQuantity(Integer productId, Integer colorId, Integer sizeId);

}
