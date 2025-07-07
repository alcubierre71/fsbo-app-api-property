package com.universo.fsbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEstimationDto {

    private double minSalePrice;
    private double maxSalePrice;
    private double minRentalPrice;
    private double maxRentalPrice;
    
}
