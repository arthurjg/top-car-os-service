package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.PecaInsumoSolicitadoDBEntity;

public interface PecaInsumoSolicitadoRepository extends JpaRepository<PecaInsumoSolicitadoDBEntity, Long> {

}
