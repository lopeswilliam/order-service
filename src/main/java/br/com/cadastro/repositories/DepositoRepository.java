package br.com.cadastro.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.entity.Deposito;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long>{

	List<Deposito> findByCnpj(String cnpj);

	Deposito deleteByCnpj(String cnpj);
	
	List<Deposito> findAll();
	
	Optional<Deposito> findById(Long id);
	
}
