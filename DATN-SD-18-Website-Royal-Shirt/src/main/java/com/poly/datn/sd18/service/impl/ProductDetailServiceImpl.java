package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.request.ProductDetailRequest;
import com.poly.datn.sd18.dto.response.ProductDetailResponse;
import com.poly.datn.sd18.dto.response.SizeResponse;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public List<ProductDetailResponse> getAllByProductId(Integer productId) {
        return productDetailRepository.getAllByProductId(productId);
    }

    @Override
    public List<SizeResponse> getListSizeAddProductDetail(Integer productId, Integer colorId) {
        return productDetailRepository.getListSizeAddProductDetail(productId, colorId);
    }

    @Override
    public ProductDetail findById(Integer id) {
        return productDetailRepository.findById(id).get();
    }

    @Override
    public ProductDetail add(ProductDetailRequest productDetailRequest) {
        ProductDetail productDetail = productDetailRequest.map(new ProductDetail());
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail update(ProductDetail productDetail, int id) {
        ProductDetail searchProductDetail = productDetailRepository.findById(id).get();
        if (searchProductDetail != null) {
            searchProductDetail.setWeight(productDetail.getWeight());
            searchProductDetail.setQuantity(productDetail.getQuantity());
            searchProductDetail.setPrice(productDetail.getPrice());
            return productDetailRepository.save(searchProductDetail);
        }
        return null;
    }

    @Override
    public ProductDetail setStatus(int id) {
        ProductDetail searchProductDetail = productDetailRepository.findById(id).get();
        if (searchProductDetail != null) {
            if (searchProductDetail.getStatus() == 1) {
                searchProductDetail.setStatus(0);
            } else {
                searchProductDetail.setStatus(1);
            }
            return productDetailRepository.save(searchProductDetail);
        }
        return null;
    }
}
