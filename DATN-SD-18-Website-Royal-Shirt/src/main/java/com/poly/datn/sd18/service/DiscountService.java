package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getAll();

    Discount findById(int id);

    List<Discount> findByName(String name);

    Discount add(Discount discount);

    Discount update(Discount discount, int id);

    Discount setStatus(int id);
}
