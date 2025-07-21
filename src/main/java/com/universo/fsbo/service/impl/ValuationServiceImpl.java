package com.universo.fsbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.UserDto;
import com.universo.fsbo.dto.mapper.PropertyMapper;
import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.entity.ValuationEntity;
import com.universo.fsbo.repository.PropertyRepository;
import com.universo.fsbo.repository.ValuationRepository;
import com.universo.fsbo.service.ValuationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j   // Logger de Lombok 
public class ValuationServiceImpl implements ValuationService {

	@Autowired
    private ValuationRepository valuationRepository;

	@Autowired
    private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyMapper propertyMapper;
	
	/**
	 * Obtener valoraciones de propiedades de un usuario
	 */
	@Override
	public List<PriceEstimationDto> getAllValuationsByUser(String userId) {
		
		List<ValuationEntity> valuations = valuationRepository.findByUserId(userId);
		
	    return valuations.stream()
	    		.map(propertyMapper::convertToEstimationDto)
	            .toList();
	}
	
	/**
	 * Creacion del registro de Valoracion de la Propiedad
	 */
	@Override
	public PriceEstimationDto saveValuation(PriceEstimationDto priceEstimation) {
	    
//		// Obtener Entidad a partir del Id almacenado en Valuation
//	    PropertyEntity propertyEntity = propertyRepository.findById(priceEstimation.getPropertyId())
//		    .map(p -> {
//		        log.info("Accedida propiedad {}", p.getId());
//		        return p;
//		    })
//	        .orElseThrow(() -> new RuntimeException("Property not found with ID: " + priceEstimation.getPropertyId()));
//
//	    // Creamos la entidad de valoración
//	    ValuationEntity valuation = new ValuationEntity();
//	    valuation.setProperty(propertyEntity);
//	    valuation.setUserId(userDto.getId());
//	    //valuation.setValuationDate(LocalDate.now());
//
//	    valuation.setMinSalePrice(priceEstimation.getMinSalePrice());
//	    valuation.setMaxSalePrice(priceEstimation.getMaxSalePrice());
//	    valuation.setMinRentalPrice(priceEstimation.getMinRentalPrice());
//	    valuation.setMaxRentalPrice(priceEstimation.getMaxRentalPrice());

	    ValuationEntity valuation = propertyMapper.convertToValuationEntity(priceEstimation);
	    
	    // Le asociamos el usuario a la Valoracion de la propiedad
	    String userId = priceEstimation.getUserId(); 
	    valuation.setUserId(userId);
	    
	    // Guardamos la valoración
	    ValuationEntity valuationSaved = valuationRepository.save(valuation);

	    // Creamos y devolvemos el DTO de respuesta
//	    PriceEstimationDto response = new PriceEstimationDto();
//	    response.setValuationId(valuation.getId());
//	    response.setPropertyId(valuation.getProperty().getId());
//	    response.setUserId(valuation.getUserId());
//	    response.setMinSalePrice(valuation.getMinSalePrice());
//	    response.setMaxSalePrice(valuation.getMaxSalePrice());
//	    response.setMinRentalPrice(valuation.getMinRentalPrice());
//	    response.setMaxRentalPrice(valuation.getMaxRentalPrice());
//	    response.setValuationDate(valuation.getValuationDate());
	    
	    PriceEstimationDto response = propertyMapper.convertToEstimationDto(valuationSaved);

	    return response;
	    
	}

}
