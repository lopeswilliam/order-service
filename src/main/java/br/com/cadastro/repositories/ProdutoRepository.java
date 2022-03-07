package br.com.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.entity.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByCodigo(Integer codigo);

	Produto deleteByCodigo(Integer codigo);
}
