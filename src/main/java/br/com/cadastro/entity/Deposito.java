package br.com.cadastro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Deposito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6249733210874767507L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fornecedorid;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String nomefantasia;
		
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String tipoempresa;
	
	@Transient
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNomefantasia() {
		return nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}



	public String getTipoempresa() {
		return tipoempresa;
	}

	public void setTipoempresa(String tipoempresa) {
		this.tipoempresa = tipoempresa;
	}

	public String getFornecedorid() {
		return fornecedorid;
	}

	public void setFornecedorid(String fornecedorid) {
		this.fornecedorid = fornecedorid;
	}

	



	
}
