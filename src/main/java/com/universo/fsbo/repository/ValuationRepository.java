package com.universo.fsbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universo.fsbo.entity.PropertyEntity;
import com.universo.fsbo.entity.ValuationEntity;

@Repository
public interface ValuationRepository extends JpaRepository<ValuationEntity, Long> {

	// Buscar valoraciones de inmuebles por ID de usuario
    List<ValuationEntity> findByUserId(String userId);
    
}
