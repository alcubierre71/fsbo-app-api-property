package com.universo.fsbo.service;

import java.util.List;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.UserDto;

public interface PropertyService {

	List<PropertyDto> getAllPropertiesByUser(String userId);
	//List<PriceEstimationDto> getAllValuationsByUser(String userId);
	
	PropertyDto getPropertyById (Long propertyId);
	
	PriceEstimationDto estimatePrice(PropertyDto property);	
	
	PriceEstimationDto calculatePriceRange(PropertyDto propertyDto);
	
	PropertyDto saveProperty(PropertyDto propertyDto);
	
	//PropertyDto updateProperty(PropertyDto propertyDto);
	   
}
