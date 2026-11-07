package br.com.conectavoluntario.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "area_atuacao")
public class AreaAtuacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_area_atuacao")
	private Long idAreaAtuacao;
	
	@Column(name = "nome_area", nullable= false, unique= true)
	private String nomeArea;
	
	
	public AreaAtuacao(Long idAreaAtuacao, String nomeArea) {
		super();
		this.idAreaAtuacao = idAreaAtuacao;
		this.nomeArea = nomeArea;
	}
	
	public AreaAtuacao() {
		
	}

	//GETTERS
	public Long getIdAreaAtuacao() {
		return idAreaAtuacao;
	}
	
	public String getNomeArea() {
		return nomeArea;
	}
	
	//SETTERS
	public void setIdAreaAtuacao(Long idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}
	
	public void setNomeArea(String nomeArea) {
		this.nomeArea = nomeArea;
	}
	
	

}
