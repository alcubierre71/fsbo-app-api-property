package com.universo.fsbo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEstimationDto {

	private Long valuationId;
	private Long propertyId; // ID de la propiedad asociada a la valoracion
    private String userId;
	
    private double minSalePrice;
    private double maxSalePrice;
    private double minRentalPrice;
    private double maxRentalPrice;
    
    private LocalDateTime valuationDate;
    
}
