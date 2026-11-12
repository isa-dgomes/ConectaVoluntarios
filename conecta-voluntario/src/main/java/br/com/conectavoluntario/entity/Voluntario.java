package br.com.conectavoluntario.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.com.conectavoluntario.enums.*;
import jakarta.persistence.*;


@Entity
@Table(name = "voluntario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Voluntario extends Usuario{
	
	@Column(name = "habilidades", columnDefinition = "TEXT")
	private String habilidades;
	
	//manytomany aqui por que usa entidade de banco,
	
	@ManyToMany																 
	@JoinTable(
			name="voluntario_area_interesse",
			joinColumns = @JoinColumn(name = "id_voluntario"),
			inverseJoinColumns = @JoinColumn(name= "id_area_atuacao")
			)
	private Set<AreaAtuacao> areasDeInteresse = new HashSet<>();
	
	
	//elementcollection pois usa enums
	
	@ElementCollection
	@CollectionTable(
		name = "voluntario_disponibilidade",
		joinColumns = @JoinColumn(name="id_voluntario")
			)
	@Enumerated(EnumType.STRING)
	@Column(name ="dia_semana")
	private Set<DiaSemana> disponibilidade = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(
		name= "voluntario_periodo",
		joinColumns = @JoinColumn(name = "id_voluntario")
			)
	@Enumerated(EnumType.STRING)
	@Column(name = "periodo")
	private Set<Periodo> periodo = new HashSet<>();
	
	@Column(name = "experiencia", columnDefinition = "TEXT")
	private String experiencia;
	
	
	
	public Voluntario( String nome,  String email, String senhaHash, 
			String telefone, boolean aceitouTermos, Instant dataAceiteTermos, 
			Endereco endereco, String habilidades, 
			Set<AreaAtuacao> areasDeInteresse,
			Set<DiaSemana> disponibilidade,
			Set<Periodo> periodo, 
			String experiencia) {
		
		super( nome, email, senhaHash, telefone, aceitouTermos, dataAceiteTermos, endereco);
		this.habilidades = habilidades;
		this.areasDeInteresse = areasDeInteresse;
		this.disponibilidade = disponibilidade;
		this.periodo = periodo;
		this.experiencia = experiencia;
	}
	
	public Voluntario() {
		
	}

	//GETTERS
	public String getHabilidades() {
		return habilidades;
	}
	
	public Set<AreaAtuacao> getAreasDeInteresse() {
		return areasDeInteresse;
	}
	
	public Set<DiaSemana> getDisponibilidade() {
		return disponibilidade;
	}
	
	public Set<Periodo> getPeriodo() {
		return periodo;
	}
	
	public String getExperiencia() {
		return experiencia;
	}
	
	//SETTERS
	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}
	
	public void setAreasDeInteresse(Set<AreaAtuacao> areasDeInteresse) {
		this.areasDeInteresse = areasDeInteresse;
	}
	
	public void setDisponibilidade(Set<DiaSemana> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	public void setPeriodo(Set<Periodo> periodo) {
		this.periodo = periodo;
	}
	
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	
	//METODOS
	
	  
	
	//adiciona e remove area de interesse da lista
	public void adicionarAreaDeInteresse(AreaAtuacao area) {
		areasDeInteresse.add(area);
	}
	
	public void removerAreaDeInteresse(AreaAtuacao area) {
		areasDeInteresse.remove(area);
	}
	
	//adiciona e remove disponibilidades de dias da semana da lista de dias da semana
	public void adicionarDisponibilidade(DiaSemana dia) {
		disponibilidade.add(dia);
	}
	
	public void removerDisponibilidade(DiaSemana dia) {
		disponibilidade.remove(dia);
	}
	
	
	//adiciona e remove periodo da lista de periodos
	public void adicionarPeriodo(Periodo p) {
		periodo.add(p);
	}
	
	public void removerPeriodo(Periodo p) {
		periodo.remove(p);
	}
	
	
	

}
