package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.ProductDetailRequest;
import com.poly.datn.sd18.dto.ProductDetailResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.entity.Size;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetailResponse> getAllByProductId(Integer productId);

    List<Size> getListSizeAddProductDetail(Integer colorId,Integer productId);

    ProductDetail findById(Integer id);

    ProductDetail add(ProductDetailRequest productDetailRequest);

    ProductDetail update(ProductDetail productDetail, int id);

    ProductDetail setStatus(int id);

}
