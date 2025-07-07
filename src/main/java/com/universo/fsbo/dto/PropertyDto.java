package com.universo.fsbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {

    // Block 1: Basic property data
    private String propertyType;
    private int builtArea;
    private int bedrooms;
    private int bathrooms;
    private int floor;
    private String condition;
    private String description;

    // Block 2: Extra features (booleans)
    private boolean isExterior;
    private boolean hasElevator;
    private boolean hasParking;
    private boolean hasStorageRoom;
    private boolean hasAirConditioning;
    private boolean hasBalconyOrTerrace;
    private boolean hasPool;

    // Block 3: Location
    private String country;
    private String region;
    private String province;
    private String city;
    private String district;
    private String neighborhood;
    
}
