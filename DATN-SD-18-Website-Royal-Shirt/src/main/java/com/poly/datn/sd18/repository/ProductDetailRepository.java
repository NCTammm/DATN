package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query("select c from ProductDetail c where c.product.id =:productId")
    List<ProductDetail> getAllProductDetailByProductId(@Param("productId") Integer productId);

    @Query(value = """
            SELECT
                pd.*
            FROM
                product_details pd
                INNER JOIN
                    products p ON pd.product_id = p.id
                INNER JOIN
                    colors c ON pd.color_id = c.id
                INNER JOIN
                    sizes s ON pd.size_id = s.id
                LEFT JOIN (
                    SELECT product_id, MIN(id) AS min_image_id
                    FROM images
                    GROUP BY product_id
                ) AS min_images ON pd.product_id = min_images.product_id
                LEFT JOIN
                    images i ON min_images.min_image_id = i.id
                WHERE
                    p.id =:productId AND p.status = 0
                    AND pd.size_id =:sizeId AND s.status = 0
                    AND pd.color_id =:colorId AND c.status = 0;
        """, nativeQuery = true)
    ProductDetail findByProductIdAndColorIdAndSizeId(@Param("productId") Integer productId,
                                                     @Param("sizeId") Integer sizeId,
                                                     @Param("colorId") Integer colorId);
    @Query("select pd from ProductDetail pd where pd.id = :productDetailId")
    ProductDetail findByProductDetailId(@Param("productDetailId") Integer productDetailId);

    @Query(value = """
                SELECT pd.*
                FROM cart_details cd
                INNER JOIN product_details pd ON cd.product_detail_id = pd.id
                WHERE cd.id IN :cartDetailId
            """, nativeQuery = true)
    List<ProductDetail> findProductDetailIdByCartDetailId(@Param("cartDetailId") List<Integer> cartDetailId);

    @Query(value = """
                SELECT
                    pd.*
                FROM
                    product_details pd
                JOIN
                    products p ON pd.product_id = p.id
                JOIN
                    sizes s ON pd.size_id = s.id
                JOIN
                    colors c ON pd.color_id = c.id
                WHERE
                    p.id = :productId
                    AND s.id = :sizeId
                    AND c.id = :colorId
                
            """, nativeQuery = true)
    ProductDetail showQuantity(@Param("productId") Integer productId,
                               @Param("colorId") Integer colorId,
                               @Param("sizeId") Integer sizeId);
}
