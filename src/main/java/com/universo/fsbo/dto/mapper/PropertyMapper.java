package com.universo.fsbo.dto.mapper;

import org.springframework.stereotype.Component;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.entity.ValuationEntity;

@Component
public class PropertyMapper {

	/**
	 * 
	 * @param entity
	 * @return
	 */
    public PropertyDto convertToEntityDto(PropertyEntity entity) {
    	
        PropertyDto dto = new PropertyDto();
        
        dto.setPropertyId(entity.getId());
        dto.setUserId(entity.getUserId());
        
        dto.setPropertyType(entity.getPropertyType());
        dto.setBuiltArea(entity.getBuiltArea());
        dto.setBedrooms(entity.getBedrooms());
        dto.setBathrooms(entity.getBathrooms());
        dto.setFloor(entity.getFloor());
        dto.setCondition(entity.getCondition());
        dto.setDescription(entity.getDescription());

        dto.setExterior(entity.isExterior());
        dto.setHasElevator(entity.isHasElevator());
        dto.setHasParking(entity.isHasParking());
        dto.setHasStorageRoom(entity.isHasStorageRoom());
        dto.setHasAirConditioning(entity.isHasAirConditioning());
        dto.setHasBalconyOrTerrace(entity.isHasBalconyOrTerrace());
        dto.setHasPool(entity.isHasPool());

        dto.setCountry(entity.getCountry());
        dto.setRegion(entity.getRegion());
        dto.setProvince(entity.getProvince());
        dto.setCity(entity.getCity());
        dto.setDistrict(entity.getDistrict());
        dto.setNeighborhood(entity.getNeighborhood());

        return dto;
        
    }

    /**
     * 
     * @param valuation
     * @return
     */
    public PriceEstimationDto convertToEstimationDto(ValuationEntity valuation) {
    	
        PriceEstimationDto estimation = new PriceEstimationDto();

        estimation.setValuationId(valuation.getId());
        estimation.setPropertyId(valuation.getProperty().getId());
        estimation.setUserId(valuation.getUserId());
        estimation.setValuationDate(valuation.getValuationDate());
        
        estimation.setMinSalePrice(valuation.getMinSalePrice());
        estimation.setMaxSalePrice(valuation.getMaxSalePrice());
        estimation.setMinRentalPrice(valuation.getMinRentalPrice());
        estimation.setMaxRentalPrice(valuation.getMaxRentalPrice());

        return estimation;
        
    }
}
