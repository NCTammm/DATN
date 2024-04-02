package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.dto.request.ProductRequest;
import com.poly.datn.sd18.dto.response.ProductResponse;
import com.poly.datn.sd18.entity.Product;
import com.poly.datn.sd18.repository.ProductRepository;
import com.poly.datn.sd18.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product add(ProductRequest productRequest) {
        Product product = productRequest.map(new Product());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, int id) {
        Product searchProduct = productRepository.findById(id).get();
        if (searchProduct != null) {
            searchProduct.setName(product.getName());
            return productRepository.save(searchProduct);
        }
        return null;
    }

    @Override
    public Product setStatus(int id) {
        Product searchProduct = productRepository.findById(id).get();
        if (searchProduct != null) {
            if (searchProduct.getStatus() == 1) {
                searchProduct.setStatus(0);
            } else {
                searchProduct.setStatus(1);
            }
            return productRepository.save(searchProduct);
        }
        return null;
    }

    @Override
    public Integer quantityByColorId(Integer productId, Integer colorId) {
        return productRepository.quantityByColorId(productId,colorId);
    }

    @Override
    public Integer quantityBySizeId(Integer productId, Integer colorId) {
        return productRepository.quantityBySizeId(productId,colorId);
    }
}
