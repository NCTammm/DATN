package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findStaffByEmail(String email);
    List<Staff> existsByEmail(String email);
}
