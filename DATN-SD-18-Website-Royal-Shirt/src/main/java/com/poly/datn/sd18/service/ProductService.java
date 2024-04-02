package com.poly.datn.sd18.service;

import com.poly.datn.sd18.dto.request.ProductRequest;
import com.poly.datn.sd18.dto.response.ProductResponse;
import com.poly.datn.sd18.entity.Product;

import java.util.List;
import com.poly.datn.sd18.entity.Order;
import com.poly.datn.sd18.entity.Product;
public interface ProductService {
    List<ProductResponse> getAll();

    Product findById(int id);

    List<Product> findByName(String name);

    Product add(ProductRequest productRequest);

    Product update(Product product, int id);

    Product setStatus(int id);

    Integer quantityByColorId(Integer productId, Integer colorId);

    Integer quantityBySizeId(Integer productId, Integer colorId);

    List<Product> getListProduct();

}
