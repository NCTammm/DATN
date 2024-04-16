package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Staff;
import com.poly.datn.sd18.model.dto.StaffDTO;
import com.poly.datn.sd18.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/rest/staffs")
public class StaffRestController {
    private final StaffService staffService;

    @PostMapping("/validateDuplicateEmail")
    public ResponseEntity<?> validateDuplicateEmail(@RequestBody StaffDTO staffDTO) {
        List<Staff> lists = staffService.existsByEmail(staffDTO.getEmail());
        boolean existsEmail = false;
        if(lists.isEmpty()){
            existsEmail = true;
        }
        return ResponseEntity.ok().body("Email is available");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StaffDTO staffDTO) {
        Staff staff = staffService.create(staffDTO);
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> setStatusStaff(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(staffService.setStatusStaff(id));
    }

    @GetMapping("/formUpdate/{id}")
    public ResponseEntity<?> formUpdate(@PathVariable("id") Integer staffId,
                                        Model model) {
        Staff staff = staffService.findStaffById(staffId);
        if (staff != null) {
            model.addAttribute("staff", staff);
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStaff(@Valid @RequestBody StaffDTO staffDTO,
                                         @RequestParam("imageStaff") MultipartFile file,
                                         @PathVariable("id") Integer id,
                                         BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors()
                        .stream().map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }else {
                Staff staff = staffService.updateStaff(staffDTO, id, file);
                return ResponseEntity.ok(staff);
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
