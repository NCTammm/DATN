package com.poly.datn.sd18.service;

import com.poly.datn.sd18.entity.Staff;
import com.poly.datn.sd18.model.dto.StaffDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();
    Page<Staff> getAllStaffPages(Integer pageNo);
    List<Staff> searchStaffByName(String name);
    List<Staff> searchStaffByStatus(Integer status);
    Staff createStaff(Staff staff);
    Staff findStaffById(Integer id);
    Staff updateStaff(Staff staff, Integer id);
    Staff setStatusStaff(Integer id);
}
