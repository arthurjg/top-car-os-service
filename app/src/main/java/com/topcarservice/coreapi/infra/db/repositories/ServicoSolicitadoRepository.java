package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.ServicoSolicitadoDBEntity;

public interface ServicoSolicitadoRepository extends JpaRepository<ServicoSolicitadoDBEntity, Long> {

}
