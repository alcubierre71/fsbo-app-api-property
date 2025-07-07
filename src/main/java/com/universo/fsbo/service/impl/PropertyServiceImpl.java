package com.universo.fsbo.service.impl;

import org.springframework.stereotype.Service;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	/**
	 * Calcular Rango de precio del valor estimado de la vivienda
	 */
    @Override
    public PriceEstimationDto calculatePriceRange(PropertyDto property) {
        double basePricePerM2 = estimatePricePerM2(property);
        double builtArea = property.getBuiltArea(); // superficie-construida
        double salePrice = builtArea * basePricePerM2;

        // Rango de venta (±10%)
        double minSalePrice = salePrice * 0.9;
        double maxSalePrice = salePrice * 1.1;

        // Estimación de alquiler: 0.4% del valor de venta mensual
        double minRentalPrice = minSalePrice * 0.004;
        double maxRentalPrice = maxSalePrice * 0.0045;

        PriceEstimationDto dto = new PriceEstimationDto();
        dto.setMinSalePrice(minSalePrice);
        dto.setMaxSalePrice(maxSalePrice);
        dto.setMinRentalPrice(minRentalPrice);
        dto.setMaxRentalPrice(maxRentalPrice);

        return dto;
    }

    /**
     * Calcular precio estimado por metro cuadrado
     * @param property
     * @return
     */
    private double estimatePricePerM2(PropertyDto property) {
        // Puedes personalizar este valor según zona o estado
        double base = 2200.0;

        switch (property.getCondition().toLowerCase()) {
            case "reformed":
                base *= 1.1;
                break;
            case "to renovate":
                base *= 0.85;
                break;
            default:
                base *= 1.0;
                break;
        }

        if (!property.isExterior()) {
            base *= 0.95;
        }

        if (!property.isHasElevator()) {
            base *= 0.93;
        }

        return base;
    }

}
