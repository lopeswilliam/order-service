package br.com.cadastro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.entity.OrderPedido;

@Repository
public interface OrderRepository extends JpaRepository<OrderPedido, Long>{
	
	Optional<OrderPedido> findById(Long id);
	
}
