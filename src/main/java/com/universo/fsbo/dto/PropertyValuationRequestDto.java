package com.universo.fsbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValuationRequestDto {

    private UserDto user;
    private PropertyDto property;
    
}
