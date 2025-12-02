package com.levelup.repository;

import com.levelup.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
    List<ShippingAddress> findByUser_Correo(String correo);

}
