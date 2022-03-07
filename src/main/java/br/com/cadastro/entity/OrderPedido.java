package br.com.cadastro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OrderPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7228799568869995221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String datapedido;

	@Column(nullable = false)
	private String codigoproduto;

	public String getDatapedido() {
		return datapedido;
	}

	public void setDatapedido(String datapedido) {
		this.datapedido = datapedido;
	}

	public String getCodigoproduto() {
		return codigoproduto;
	}

	public void setCodigoproduto(String codigoproduto) {
		this.codigoproduto = codigoproduto;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}