package com.levelup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "SHIPPING_ADDRESSES")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "correo")
    @JsonIgnore
    private User user;

    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 255)
    private String calle;
    @Column(nullable = false, length = 100)
    private String ciudad;
}