package com.universo.fsbo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "VALUATIONS")
public class ValuationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Valoracion economica
    @Column(name="min_sale_price")
    private Double minSalePrice;
    @Column(name="max_sale_price")
    private Double maxSalePrice;
    @Column(name="min_rental_price")
    private Double minRentalPrice;
    @Column(name="max_rental_price")
    private Double maxRentalPrice;

    // Fecha en que se realiza la valoracion
    @CreationTimestamp
    @Column(name="valuation_date")
    private LocalDateTime valuationDate;

    // Usuario que solicita la valoracion
    @Column(name="user_id")
    private String userId;

    // Una Propiedad puede tener varias Valoraciones con fechas distintas
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private PropertyEntity property;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
