package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Cart;
import com.poly.datn.sd18.entity.CartDetail;
import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    CartDetail findByCartAndProductDetail(Cart cart, ProductDetail productDetail);
    @Query(value = """
                SELECT
                    cd.*
                FROM
                    cart_details cd
                INNER JOIN
                    carts c ON cd.cart_id = c.id
                INNER JOIN
                    customers cst ON c.customer_id = cst.id
                WHERE
                    cst.id =:customerId
            """, nativeQuery = true)
    List<CartDetail> findCartDetailByCustomer(@Param("customerId") Integer customerId);

    @Modifying
    @Query("delete from CartDetail cd where cd.productDetail.id = :productDetailId and cd.cart.customer.id = :customerId")
    void deleteIdProductDetailAndIdCustomer(@Param("productDetailId") Integer productDetailId,
                                            @Param("customerId") Integer customerId);

    @Query("select sum(cd.price) from CartDetail cd where cd.cart.customer.id = :customerId")
    Float getSumPriceByCustomerId(@Param("customerId") Integer customerId);
}
