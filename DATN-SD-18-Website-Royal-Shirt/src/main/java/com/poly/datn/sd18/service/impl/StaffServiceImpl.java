package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Staff;
import com.poly.datn.sd18.repository.StaffRepository;
import com.poly.datn.sd18.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> getAllActive() {
        return staffRepository.getAllActive();
    }

    @Override
    public Staff findById(Integer id) {
        return staffRepository.findById(id).get();
    }
}
