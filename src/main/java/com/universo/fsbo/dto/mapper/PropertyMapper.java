package com.universo.fsbo.dto.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.entity.ValuationEntity;
import com.universo.fsbo.repository.PropertyRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j   // Logger de Lombok 
public class PropertyMapper {

	@Autowired
    private PropertyRepository propertyRepository;
	
	// Logger
	//private static final Logger log = LoggerFactory.getLogger(PropertyMapper.class);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
    public PropertyDto convertToPropertyDto(PropertyEntity entity) {
    	
        PropertyDto dto = new PropertyDto();
        
        dto.setPropertyId(entity.getId());
        dto.setUserId(entity.getUserId());
        
        dto.setAlias(entity.getAlias());
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

    /**
     * Mapper para convertir PropertyDto en la entidad PropertyEntity
     * @param propertyDto
     * @return
     */
    public PropertyEntity convertToPropertyEntity (PropertyDto propertyDto) {
    	
        PropertyEntity entity = new PropertyEntity();
        
        if (propertyDto.getPropertyId() != null && propertyDto.getPropertyId() > 0L) {
        	entity.setId(propertyDto.getPropertyId());
        }

        // Datos del inmueble
        entity.setAlias(propertyDto.getAlias());
        entity.setPropertyType(propertyDto.getPropertyType());
        entity.setBuiltArea(propertyDto.getBuiltArea());
        entity.setBedrooms(propertyDto.getBedrooms());
        entity.setBathrooms(propertyDto.getBathrooms());
        entity.setFloor(propertyDto.getFloor());
        entity.setCondition(propertyDto.getCondition());
        entity.setDescription(propertyDto.getDescription());

        entity.setExterior(propertyDto.isExterior());
        entity.setHasElevator(propertyDto.isHasElevator());
        entity.setHasParking(propertyDto.isHasParking());
        entity.setHasStorageRoom(propertyDto.isHasStorageRoom());
        entity.setHasAirConditioning(propertyDto.isHasAirConditioning());
        entity.setHasBalconyOrTerrace(propertyDto.isHasBalconyOrTerrace());
        entity.setHasPool(propertyDto.isHasPool());

        entity.setCountry(propertyDto.getCountry());
        entity.setRegion(propertyDto.getRegion());
        entity.setProvince(propertyDto.getProvince());
        entity.setCity(propertyDto.getCity());
        entity.setDistrict(propertyDto.getDistrict());
        entity.setNeighborhood(propertyDto.getNeighborhood());

        // Estimaciones
//        entity.setMinSalePrice(estimationDto.getMinSalePrice());
//        entity.setMaxSalePrice(estimationDto.getMaxSalePrice());
//        entity.setMinRentalPrice(estimationDto.getMinRentalPrice());
//        entity.setMaxRentalPrice(estimationDto.getMaxRentalPrice());

        // Usuario asociado
        entity.setUserId(propertyDto.getUserId());

    	return entity;
    	
    }
    
    
    
    /**
     * Mapper para convertir un Dto a una Entity
     * @param estimation
     * @return
     */
    public ValuationEntity convertToValuationEntity (PriceEstimationDto estimation) {
    	
    	ValuationEntity valuation = new ValuationEntity();
    	 
		// Obtener Entidad a partir del Id almacenado en Valuation
	    PropertyEntity propertyEntity = propertyRepository.findById(estimation.getPropertyId())
	    	.map(p -> {
	    	    log.info("Accedida propiedad {}", p.getId());
	    	    return p;
	    	})
	        .orElseThrow( () -> new RuntimeException("Property not found with ID: " + estimation.getPropertyId()) );    	
    	
    	valuation.setId(estimation.getValuationId());
    	valuation.setProperty(propertyEntity);
    	valuation.setUserId(estimation.getUserId());
    	valuation.setValuationDate(estimation.getValuationDate());
    	
    	valuation.setMinSalePrice(estimation.getMinSalePrice());
    	valuation.setMaxSalePrice(estimation.getMaxSalePrice());
    	valuation.setMinRentalPrice(estimation.getMinRentalPrice());
    	valuation.setMaxRentalPrice(estimation.getMaxRentalPrice());
    	
    	return valuation;
    	
    }
    
    
    
    
}
