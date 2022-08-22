package br.com.big.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.big.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);

}
