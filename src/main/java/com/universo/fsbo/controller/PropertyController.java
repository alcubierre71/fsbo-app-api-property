package com.universo.fsbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.service.PropertyService;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

		@Autowired
	    private PropertyService propertyService;


	    @PostMapping("/valuation")
	    public ResponseEntity<PriceEstimationDto> estimatePrice(@RequestBody PropertyDto propertyDto) {
	        PriceEstimationDto estimation = propertyService.calculatePriceRange(propertyDto);
	        return ResponseEntity.ok(estimation);
	    }
	    
}
