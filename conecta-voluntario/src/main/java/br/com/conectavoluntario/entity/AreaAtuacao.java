package br.com.conectavoluntario.entity;

public class AreaAtuacao {
	
	private Long idAreaAtuacao;
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
