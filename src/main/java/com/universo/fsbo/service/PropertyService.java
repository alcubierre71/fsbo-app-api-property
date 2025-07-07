package com.universo.fsbo.service;

import java.util.List;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.UserDto;

public interface PropertyService {

	List<PropertyDto> getAllPropertiesByUser(String userId);
	
	PriceEstimationDto calculatePriceRange(PropertyDto propertyDto);
	
	PriceEstimationDto saveProperty(PropertyDto propertyDto, PriceEstimationDto estimationDto, UserDto userDto);
	   
}
