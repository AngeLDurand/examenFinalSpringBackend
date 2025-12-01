package com.levelup.repository;

import com.levelup.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser_CorreoOrderByFechaDesc(String correo);
}
