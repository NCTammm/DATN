package com.poly.datn.sd18.repository;

import com.poly.datn.sd18.entity.Order;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o")
    List<Order> getAll();

    @Query("SELECT o FROM Order o WHERE o.type = :type")
    List<Order> getByType(boolean type);

    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> getByStatus(int status);

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order getById(int id);

    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.type = :type")
    List<Order> getByStatusAndType(int status, boolean type);

    @Query("Select SUM(o.totalPrice) From Order o")
    Float totalOrders();

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE MONTH(o.successDate) = MONTH(:month)")
    Float totalOrdersByMonth(@Param("month") Date month);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE Year(o.successDate) = Year(:year)")
    Float totalOrdersByYear(@Param("year") Date year);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.successDate =:date")
    Float totalOrdersByDate(@Param("date") Date date);

    @Query("Select COUNT(o.id) From Order o")
    int countOrders();

    @Query("Select COUNT(o.id) From Order o WHERE MONTH(o.successDate) = MONTH(:month)")
    int countOrdersByMonth(@Param("month") Date month);

    @Query("Select COUNT(o.id) From Order o WHERE Year(o.successDate) = Year(:year)")
    int countOrdersByYear(@Param("year") Date year);

    @Query("Select COUNT(o.id) From Order o WHERE o.successDate =:date")
    int countOrdersByDate(@Param("date") Date date);

    @Procedure(name = "thongkedonhang")
    List<Object> thongkedonhang(@Param("Nam") int Nam);

    @Procedure(name = "thongKeSoSanPham")
    List<Object> thongKeSoSanPham(@Param("Nam") int Nam);

}
