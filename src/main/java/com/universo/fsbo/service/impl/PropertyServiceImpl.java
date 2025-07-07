package com.universo.fsbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.UserDto;
import com.universo.fsbo.dto.mapper.PropertyMapper;
import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.repository.PropertyRepository;
import com.universo.fsbo.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
    private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertyMapper propertyMapper;

	/**
	 * Obtener propiedades de un usuario
	 */
	public List<PropertyDto> getAllPropertiesByUser(String userId) {
	    List<PropertyEntity> properties = propertyRepository.findByUserId(userId);
	    return properties.stream()
	    		.map(propertyMapper::convertToDto)
	            .toList();
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
    public PriceEstimationDto saveProperty(PropertyDto propertyDto, PriceEstimationDto estimationDto, UserDto userDto) {
        PropertyEntity entity = new PropertyEntity();

        // Datos del inmueble
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
        entity.setMinSalePrice(estimationDto.getMinSalePrice());
        entity.setMaxSalePrice(estimationDto.getMaxSalePrice());
        entity.setMinRentalPrice(estimationDto.getMinRentalPrice());
        entity.setMaxRentalPrice(estimationDto.getMaxRentalPrice());

        // Usuario asociado
        entity.setUserId(userDto.getId());

        // Guardar en base de datos
        propertyRepository.save(entity);

        return estimationDto;
    }
    
}
