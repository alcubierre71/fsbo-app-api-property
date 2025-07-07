package com.universo.fsbo.service;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;

public interface PropertyService {

	PriceEstimationDto calculatePriceRange(PropertyDto propertyDto);
	   
}
