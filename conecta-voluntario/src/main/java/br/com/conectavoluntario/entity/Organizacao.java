package br.com.conectavoluntario.entity;

import java.time.Instant;

import jakarta.persistence.*;


@Entity
@Table(name ="organizacao")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Organizacao extends Usuario{
	
	@Column(name= "razao_social")
	private String razaoSocial;
	
	@Column(name = "cnpj", unique = true)
	private String cnpj;
	
	@Column(name= "descricao", nullable= false)
	private String descricao;
	
	@Column(name= "tipo_organizacao")
	private String tipoOrganizacao;
	
	@Column(name= "responsavel", nullable = false)
	private String responsavel;
	private String logo;
	
	

	public Organizacao( String nome, String email, String senhaHash, 
			String telefone, boolean aceitouTermos,Instant dataAceiteTermos, 
			Endereco endereco,String razaoSocial, String cnpj, 
			String descricao, String tipoOrganizacao,
			String responsavel, String logo) {
		
		super(nome, email, senhaHash, telefone, aceitouTermos, dataAceiteTermos, endereco);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.descricao = descricao;
		this.tipoOrganizacao = tipoOrganizacao;
		this.responsavel = responsavel;
		this.logo = logo;
	}
	
	public Organizacao() {}
	

	//GETTERS
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getTipoOrganizacao() {
		return tipoOrganizacao;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public String getLogo() {
		return logo;
	}
	
	//SETTERS
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setTipoOrganizacao(String tipoOrganizacao) {
		this.tipoOrganizacao = tipoOrganizacao;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

}
