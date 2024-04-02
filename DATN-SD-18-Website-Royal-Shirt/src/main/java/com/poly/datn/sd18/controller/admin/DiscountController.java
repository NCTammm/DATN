package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.dto.response.ProductResponse;
import com.poly.datn.sd18.entity.Discount;
import com.poly.datn.sd18.service.DiscountService;
import com.poly.datn.sd18.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping()
    public String getAll(Model model) {
        List<Discount> lists = discountService.getAll();
        model.addAttribute("lists", lists);
        return "/admin/discount/index";
    }

}
