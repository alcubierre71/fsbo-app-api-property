package com.universo.fsbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universo.fsbo.dto.PriceEstimationDto;
import com.universo.fsbo.dto.PropertyDto;
import com.universo.fsbo.dto.PropertyValuationRequestDto;
import com.universo.fsbo.dto.UserDto;
import com.universo.fsbo.service.PropertyService;
import com.universo.fsbo.service.ValuationService;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

		@Autowired
	    private PropertyService propertyService;
		
		@Autowired
	    private ValuationService valuationService;

		/**
		 * Obtencion de todas las Properties solicitadas por un usuario
		 * @param userId
		 * @return
		 */
		@GetMapping("/user/{userId}/properties")
		public ResponseEntity<List<PropertyDto>> getAllUserProperties(@PathVariable String userId) {
		    List<PropertyDto> properties = propertyService.getAllPropertiesByUser(userId);
		    return ResponseEntity.ok(properties);
		}

		/**
		 * Obtencion de todas las Valoraciones de Properties solicitadas por un usuario
		 * @param userId
		 * @return
		 */
		@GetMapping("/user/{userId}/valuations")
		public ResponseEntity<List<PriceEstimationDto>> getAllUserValuations(@PathVariable String userId) {
		    List<PriceEstimationDto> valuations = valuationService.getAllValuationsByUser(userId);
		    return ResponseEntity.ok(valuations);
		}

		/**
		 * Envio de una Property por parte del usuario para su posterior Valoracion por parte del Agente IA
		 * Una vez valorada, se almacenan los datos en BBDD 
		 * @param request
		 * @return
		 */
	    @PostMapping("/valuation")
	    public ResponseEntity<PriceEstimationDto> estimatePrice(@RequestBody PropertyValuationRequestDto request) {

	        PropertyDto propertyDto = request.getProperty();
	        UserDto userDto = request.getUser();
	        
	        // logica de valoracion
	        PriceEstimationDto estimation = propertyService.calculatePriceRange(propertyDto);

	        // logica de guardado de la Property
	        PropertyDto propertyDtoSaved = new PropertyDto();
	        // Primera Valoracion de la Propiedad
	        if (propertyDto.getPropertyId() == null || propertyDto.getPropertyId() <= 0L) {
	        	propertyDtoSaved = propertyService.saveProperty(propertyDto, userDto);
	        } else {
	        	// Nueva Valoracion de una Propiedad ya existente
	        	propertyDtoSaved = propertyDto;
	        }
	        
	        // Enlazamos la Valoracion con el Id de la Propiedad
	        estimation.setPropertyId(propertyDtoSaved.getPropertyId());
	        
	        // logica de guardado de Valuation de Property
	        PriceEstimationDto estimationSaved = valuationService.saveValuation(estimation, userDto);

	        return ResponseEntity.ok(estimationSaved);
	    	
	    }
	    
}
