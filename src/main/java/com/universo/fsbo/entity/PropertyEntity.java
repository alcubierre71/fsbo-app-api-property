package com.universo.fsbo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PROPERTIES")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyType;
    private int builtArea;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private String condition;
    private String description;

    private boolean isExterior;
    private boolean hasElevator;
    private boolean hasParking;
    private boolean hasStorageRoom;
    private boolean hasAirConditioning;
    private boolean hasBalconyOrTerrace;
    private boolean hasPool;

    private String country;
    private String region;
    private String province;
    private String city;
    private String district;
    private String neighborhood;

    // Valores estimados (respuesta del servicio)
    private Double minSalePrice;
    private Double maxSalePrice;
    private Double minRentalPrice;
    private Double maxRentalPrice;

    // Usuario que solicito la valoración
    private String userId;

    // Fecha de creacion automatica
    @CreationTimestamp
    private LocalDateTime createdAt;
}
