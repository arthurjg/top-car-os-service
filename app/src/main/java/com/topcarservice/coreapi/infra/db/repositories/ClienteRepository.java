package com.topcarservice.coreapi.infra.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topcarservice.coreapi.infra.db.entities.ClienteDBEntity;

public interface ClienteRepository extends JpaRepository<ClienteDBEntity, Long> {

}
