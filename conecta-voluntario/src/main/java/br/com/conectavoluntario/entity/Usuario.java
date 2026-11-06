package br.com.conectavoluntario.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name= "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", nullable = false, unique = true)
	private Endereco endereco;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name ="nome", nullable = false )
	private String nome;
	
	@Column(name = "email", nullable = false, unique= true)
	private String email;
	
	@Column(name = "senha_hash", nullable= false)
	private String senhaHash;

	@Column(name = "telefone", nullable= false)
	private String telefone;
	
	@Column(name = "aceitou_termos", nullable= false)
	private boolean aceitouTermos;
	
	@Column(name = "data_aceite_termos")
	private Instant dataAceiteTermos;
	
	
	public Usuario(String nome, String email, String senhaHash, String telefone, boolean aceitouTermos,
			Instant dataAceiteTermos, Endereco endereco) {
		this.nome = nome;
		this.email = email;
		this.senhaHash = senhaHash;
		this.telefone = telefone;
		this.aceitouTermos = aceitouTermos;
		this.dataAceiteTermos = dataAceiteTermos;
		this.endereco = endereco;
	}
	
	public Usuario() {
		
	}
	
	

	//GETTERS
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public String getSenhaHash() {
		return senhaHash;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public boolean isAceitouTermos() {
		return aceitouTermos;
	}
	
	public Instant getDataAceiteTermos() {
		return dataAceiteTermos;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	
	//SETTERS
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setAceitouTermos(boolean aceitouTermos) {
		this.aceitouTermos = aceitouTermos;
	}
	
	public void setDataAceiteTermos(Instant dataAceiteTermos) {
		this.dataAceiteTermos = dataAceiteTermos;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

}
