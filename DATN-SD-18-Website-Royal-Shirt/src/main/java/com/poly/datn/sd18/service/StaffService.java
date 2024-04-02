package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllActive();
    Staff findById(Integer id);
}
