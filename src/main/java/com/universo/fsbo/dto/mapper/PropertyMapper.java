package com.universo.fsbo.dto.mapper;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public PropertyDto convertToDto(PropertyEntity entity) {
        PropertyDto dto = new PropertyDto();

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

    public PriceEstimationDto convertToEstimationDto(PropertyEntity entity) {
        PriceEstimationDto estimation = new PriceEstimationDto();

        estimation.setMinSalePrice(entity.getMinSalePrice());
        estimation.setMaxSalePrice(entity.getMaxSalePrice());
        estimation.setMinRentalPrice(entity.getMinRentalPrice());
        estimation.setMaxRentalPrice(entity.getMaxRentalPrice());

        return estimation;
    }
}
