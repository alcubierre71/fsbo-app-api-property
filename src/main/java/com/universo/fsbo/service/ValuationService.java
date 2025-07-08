package com.universo.fsbo.service;

import java.util.List;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.UserDto;

public interface ValuationService {

	List<PriceEstimationDto> getAllValuationsByUser(String userId);
	
	PriceEstimationDto saveValuation(PriceEstimationDto estimationDto, UserDto userDto);
	
}
