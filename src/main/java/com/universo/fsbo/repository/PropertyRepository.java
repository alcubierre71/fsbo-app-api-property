package com.universo.fsbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universo.fsbo.entity.PropertyEntity;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

    // Buscar inmuebles por ID de usuario
    List<PropertyEntity> findByUserId(String userId);

    // Opcional: buscar por ciudad o provincia
    List<PropertyEntity> findByCity(String city);
    List<PropertyEntity> findByProvince(String province);
    
}
