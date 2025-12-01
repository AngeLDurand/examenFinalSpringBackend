package com.levelup.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Corresponde a GENERATED ALWAYS AS IDENTITY
    private Long id;

    @Column(nullable = false, length = 150)
    private String modelo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Long precio;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 80)
    private String marca;

    @Column(name = "IMAGEN_URL", nullable = false, length = 500)
    private String imagenUrl;
}
