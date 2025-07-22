package com.universo.fsbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/property")
@Tag(name = "Valoraciones de Propiedades", description = "Endpoints para valorar propiedades")
@CrossOrigin
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
	    @Operation(summary = "Obtener Inmuebles del usuario", description = "Lista de inmuebles dados de alta por el usuario")
		public ResponseEntity<List<PropertyDto>> getAllUserProperties(@PathVariable String userId) {
		    List<PropertyDto> properties = propertyService.getAllPropertiesByUser(userId);
		    return ResponseEntity.ok(properties);
		}

		/**
		 * Obtencion de propiedad a partir de su Id
		 * @param propertyId
		 * @return
		 */
		@GetMapping("/id/{propertyId}")
		@Operation(summary = "Obtener Propiedad mediante Id", description = "Obtener datos de la propiedad a partir de su Id")
		public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long propertyId) {
			
			PropertyDto property = propertyService.getPropertyById(propertyId);
			
			return ResponseEntity.ok(property);
			
		}
		
		/**
		 * Crear nueva propiedad
		 * @param request
		 * @return
		 */
		@GetMapping("/save")
		@Operation(summary = "Crear Propiedad nueva", description = "Crear nueva propiedad")
		public ResponseEntity<PropertyDto> saveProperty(@RequestBody PropertyDto request) {
			
			PropertyDto property = propertyService.saveProperty(request);
			
			return ResponseEntity.ok(property);
			
		}
		
		
	
		/**
		 * Obtencion de todas las Valoraciones de Properties solicitadas por un usuario
		 * @param userId
		 * @return
		 */
		@GetMapping("/user/{userId}/valuations")
	    @Operation(summary = "Obtener Valoraciones de Inmuebles realizadas por el usuario", description = "Valoraciones de los inmuebles del usuario")
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
	    @Operation(summary = "Calculo de precios estimados de venta y alquiler del inmueble", description = "Calcula segun las caracteristicas del inmueble")
	    //public ResponseEntity<PriceEstimationDto> estimatePrice(@RequestBody PropertyValuationRequestDto request) {
	    public ResponseEntity<PriceEstimationDto> estimatePrice(@RequestBody PropertyDto request) {

	        //PropertyDto propertyDto = request.getProperty();
	        //UserDto userDto = request.getUser();
	    	PropertyDto propertyDto = request;
	        
	        // Invocacion al Agente para obtener la valoracion del inmueble
	        //PriceEstimationDto estimation = propertyService.calculatePriceRange(propertyDto);
	        PriceEstimationDto estimation = propertyService.estimatePrice(propertyDto);
	        
	        // logica de guardado de la Property
	        PropertyDto propertyDtoSaved = new PropertyDto();
	        // Primera Valoracion de la Propiedad
	        if (propertyDto.getPropertyId() == null || propertyDto.getPropertyId() <= 0L) {
	        	propertyDtoSaved = propertyService.saveProperty(propertyDto);
	        } else {
	        	// Nueva Valoracion de una Propiedad ya existente
	        	propertyDtoSaved = propertyDto;
	        }
	        
	        // Enlazamos la Valoracion con el Id de la Propiedad y el Id del Usuario
	        estimation.setPropertyId(propertyDtoSaved.getPropertyId());
	        estimation.setUserId(propertyDtoSaved.getUserId());
	        
	        // logica de guardado de Valuation de Property
	        PriceEstimationDto estimationSaved = valuationService.saveValuation(estimation);

	        return ResponseEntity.ok(estimationSaved);
	    	
	    }
	    
}
