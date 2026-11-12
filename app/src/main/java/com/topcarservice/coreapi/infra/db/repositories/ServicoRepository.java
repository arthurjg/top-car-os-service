package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.ServicoDBEntity;

public interface ServicoRepository extends JpaRepository<ServicoDBEntity, Long> {

}
