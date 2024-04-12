package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.service.CartDetailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartDetailRestController {
    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    HttpSession session;

    @Transactional
    @PostMapping("/cart-detail/increment/{idProductDetail}")
    public ResponseEntity<?> incrementQuantity(@PathVariable("idProductDetail") Integer idProductDetail) {
        Customer customer = (Customer) session.getAttribute("customer");
        cartDetailService.incrementQuantity(customer.getId(), idProductDetail);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/cart-detail/decrement/{idProductDetail}")
    public ResponseEntity<?> decrementQuantity(@PathVariable("idProductDetail") Integer idProductDetail) {
        Customer customer = (Customer) session.getAttribute("customer");
        cartDetailService.decrementQuantity(customer.getId(), idProductDetail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart-detail/{id}")
    public ResponseEntity<?> findCartDetailById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(cartDetailService.findCartDetailById(id));
    }

    @DeleteMapping("/rest/cart-detail/{id}")
    public ResponseEntity<?> deleteCartDetail(@PathVariable("id") Integer cartDetailId) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (!cartDetailService.existsById(cartDetailId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("CartDetail with ID " + cartDetailId + " not found");
        }
        cartDetailService.deleteCartDetailByIdCartDetailAndIdCustomer(cartDetailId, customer.getId());
        return ResponseEntity.ok("Delete CartDetail " + cartDetailId +" successfully");
    }
}
