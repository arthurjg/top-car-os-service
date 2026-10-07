package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.VeiculoDBEntity;

public interface VeiculoRepository extends JpaRepository<VeiculoDBEntity, Long> {

}
