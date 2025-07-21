package com.universo.fsbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.mapper.PropertyMapper;
import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.feignclient.ValuationAgentClient;
import com.universo.fsbo.repository.PropertyRepository;
import com.universo.fsbo.service.PropertyService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
    private PropertyRepository propertyRepository;
	
//	@Autowired
//    private ValuationRepository valuationRepository;

	@Autowired
	private ValuationAgentClient valuationAgentClient;
	
	@Autowired
	private PropertyMapper propertyMapper;

	/**
	 * Obtener propiedades de un usuario
	 */
	@Override
	public List<PropertyDto> getAllPropertiesByUser(String userId) {
		
	    List<PropertyEntity> properties = propertyRepository.findByUserId(userId);
	    
	    List<PropertyDto> dtos = properties.stream()
	    		.map(propertyMapper::convertToPropertyDto)
	            .toList();
	    
	    return dtos;
	    
	}
	
	/**
	 * Obtener Propiedad a partir de su Id
	 */
	@Override
	public PropertyDto getPropertyById(Long propertyId) {
		
		PropertyEntity property = propertyRepository.findById(propertyId).orElseThrow(
				() -> new EntityNotFoundException("No se encontró la propiedad con ID: " + propertyId)
		);
		
		PropertyDto dto = propertyMapper.convertToPropertyDto(property);
		
		return dto;
		
	}
	
//	/**
//	 * Obtener valoraciones de propiedades de un usuario
//	 */
//	public List<PriceEstimationDto> getAllValuationsByUser(String userId) {
//		
//		List<ValuationEntity> valuations = valuationRepository.findByUserId(userId);
//	    //List<PropertyEntity> properties = propertyRepository.findByUserId(userId);
//		
//	    return valuations.stream()
//	    		.map(propertyMapper::convertToEstimationDto)
//	            .toList();
//	}
	
	/**
	 * Invocacion al Agente IA para estimar la valoracion de la propiedad 
	 */
    @Override
    public PriceEstimationDto estimatePrice(PropertyDto property) {
    	
    	// Invocacion al Agente IA Python para obtener la valoracion de la propiedad
    	// Se invoca mediante cliente OpenFeign al endpoint de la API del Agente
    	PriceEstimationDto estimation = valuationAgentClient.estimatePrice(property);
    	
        return estimation;
        
    }
    
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
    
    /**
     * Almacenar Propiedad
     * Se almacena la propiedad junto con el usuario solicitante y los importes estimados para la vivienda
     */
    @Override
    public PropertyDto saveProperty(PropertyDto propertyDto) {
    	
//        PropertyEntity entity = new PropertyEntity();
//
//        // Datos del inmueble
//        entity.setAlias(propertyDto.getAlias());
//        entity.setPropertyType(propertyDto.getPropertyType());
//        entity.setBuiltArea(propertyDto.getBuiltArea());
//        entity.setBedrooms(propertyDto.getBedrooms());
//        entity.setBathrooms(propertyDto.getBathrooms());
//        entity.setFloor(propertyDto.getFloor());
//        entity.setCondition(propertyDto.getCondition());
//        entity.setDescription(propertyDto.getDescription());
//
//        entity.setExterior(propertyDto.isExterior());
//        entity.setHasElevator(propertyDto.isHasElevator());
//        entity.setHasParking(propertyDto.isHasParking());
//        entity.setHasStorageRoom(propertyDto.isHasStorageRoom());
//        entity.setHasAirConditioning(propertyDto.isHasAirConditioning());
//        entity.setHasBalconyOrTerrace(propertyDto.isHasBalconyOrTerrace());
//        entity.setHasPool(propertyDto.isHasPool());
//
//        entity.setCountry(propertyDto.getCountry());
//        entity.setRegion(propertyDto.getRegion());
//        entity.setProvince(propertyDto.getProvince());
//        entity.setCity(propertyDto.getCity());
//        entity.setDistrict(propertyDto.getDistrict());
//        entity.setNeighborhood(propertyDto.getNeighborhood());
//
//        // Estimaciones
////        entity.setMinSalePrice(estimationDto.getMinSalePrice());
////        entity.setMaxSalePrice(estimationDto.getMaxSalePrice());
////        entity.setMinRentalPrice(estimationDto.getMinRentalPrice());
////        entity.setMaxRentalPrice(estimationDto.getMaxRentalPrice());
//
//        // Usuario asociado
//        entity.setUserId(propertyDto.getUserId());

        PropertyEntity entity = propertyMapper.convertToPropertyEntity(propertyDto);
        
        // Guardar en base de datos
        PropertyEntity propertySaved = propertyRepository.save(entity);

        PropertyDto response = propertyMapper.convertToPropertyDto(propertySaved);
        
        return response;
        
    }
    
}
