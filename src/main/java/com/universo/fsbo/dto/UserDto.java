package com.universo.fsbo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;             // Identificador interno o externo del usuario
    private String alias;          // Nombre corto o nombre de usuario
    private String firstName;      // Nombre real
    private String lastName;       // Apellido real
    
    private String email;          // Email de contacto
    private String phoneNumber;    // Teléfono opcional
    private String preferredLang;  // Idioma preferido (ej: "es", "en")
    private String accountType;    // Tipo de cuenta (ej: "free", "premium")
    
}
