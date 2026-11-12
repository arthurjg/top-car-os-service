package com.topcarservice.coreapi.domain.usecases.ports;

import com.topcarservice.coreapi.domain.entities.Veiculo;

public interface VeiculoInputPort {

	void save(Veiculo veiculo);

	void delete(Veiculo veiculo);

}
