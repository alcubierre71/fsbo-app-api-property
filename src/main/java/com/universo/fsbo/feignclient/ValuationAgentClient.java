package com.universo.fsbo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "valuation-agent", url = "http://localhost:8081")
public interface ValuationAgentClient {

    @PostMapping("/estimate-price")
    PriceEstimationDto estimatePrice(@RequestBody PropertyDto request);
    
}
