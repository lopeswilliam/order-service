package br.com.cadastro.models;

import java.io.Serializable;

import br.com.cadastro.entity.Cliente;
import br.com.cadastro.entity.Deposito;
import br.com.cadastro.entity.Endereco;
import br.com.cadastro.entity.Produto;

public class OrderRequest implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long numeroPedido;
	
	private String codigoproduto;

	private String datapedido;

	private String cpf;
	
	private Cliente cliente;

	private Endereco endereco;

	private Produto produto;
	
	private Deposito deposito;



	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getCodigoproduto() {
		return codigoproduto;
	}

	public void setCodigoproduto(String codigoproduto) {
		this.codigoproduto = codigoproduto;
	}

	public String getDatapedido() {
		return datapedido;
	}

	public void setDatapedido(String datapedido) {
		this.datapedido = datapedido;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}

