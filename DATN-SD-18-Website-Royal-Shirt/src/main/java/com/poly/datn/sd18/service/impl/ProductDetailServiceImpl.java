package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.entity.ProductDetail;
import com.poly.datn.sd18.repository.ProductDetailRepository;
import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDetail> getAllProductDetail() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail getProductDetailByProductId(Integer productId) {
        Product idProduct = productRepository.findById(productId).orElse(null);

        return null;
    }

    @Override
    public ProductDetail findByProductIdAndColorIdAndSizeId(Integer productId,
                                                                    Integer colorId,
                                                                    Integer sizeId) {
        return productDetailRepository.findByProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
    }

    @Override
    public ProductDetail findProductDetailById(Integer id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductDetail> findProductDetailIdByCartDetailId(List<Integer> cartDetailId) {
        return productDetailRepository.findProductDetailIdByCartDetailId(cartDetailId);
    }

    @Override
    public List<ProductDetail> getProductDetailsByIds(List<Integer> id) {
        return productDetailRepository.findAllById(id);
    }

    @Override
    public ProductDetail showQuantity(Integer productId, Integer colorId, Integer sizeId) {
        return productDetailRepository.showQuantity(productId, colorId, sizeId);
    }
}
