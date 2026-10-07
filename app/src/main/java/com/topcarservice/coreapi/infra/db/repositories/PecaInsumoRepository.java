package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.PecaInsumoDBEntity;

public interface PecaInsumoRepository extends JpaRepository<PecaInsumoDBEntity, Long> {

}
