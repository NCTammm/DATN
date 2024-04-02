package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.repository.RoleRepository;
import com.poly.datn.sd18.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
}
