package br.com.conectavoluntario.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oportunidade")
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oportunidade")
    private Long idOportunidade;

    @Column(name = "id_organizacao", nullable = false)
    private Long idOrganizacao;

    @Column(name = "id_area_atuacao", nullable = false)
    private Long idAreaAtuacao;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "quantidade_vagas", nullable = false)
    private Integer quantidadeVagas;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "modalidade", nullable = false)
    private String modalidade;

    @Column(name = "local_oportunidade", nullable = false)
    private String localOportunidade;

    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "bloqueada_por_lotacao", nullable = false)
    private Boolean bloqueadaPorLotacao = false;

    @Column(name = "status_oportunidade", nullable = false)
    private String statusOportunidade;

    @Column(name = "oferece_certificado", nullable = false)
    private Boolean ofereceCertificado = false;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "exige_documentos", nullable = false)
    private Boolean exigeDocumentos;

    //Getters
    public Long getIdOportunidade() {
        return idOportunidade;
    }

    public Long getIdOrganizacao() {
        return idOrganizacao;
    }

    public Long getIdAreaAtuacao() {
        return idAreaAtuacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public String getModalidade() {
        return modalidade;
    }

    public String getLocalOportunidade() {
        return localOportunidade;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public Boolean getBloqueadaPorLotacao() {
        return bloqueadaPorLotacao;
    }

    public String getStatusOportunidade() {
        return statusOportunidade;
    }

    public Boolean getOfereceCertificado() {
        return ofereceCertificado;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public Boolean getExigeDocumentos() {
        return exigeDocumentos;
    }

    //Setters
    public void setIdOportunidade(Long idOportunidade) {
        this.idOportunidade = idOportunidade;
    }

    public void setIdOrganizacao(Long idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public void setIdAreaAtuacao(Long idAreaAtuacao) {
        this.idAreaAtuacao = idAreaAtuacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidadeVagas(Integer quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setLocalOportunidade(String localOportunidade) {
        this.localOportunidade = localOportunidade;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public void setBloqueadaPorLotacao(Boolean bloqueadaPorLotacao) {
        this.bloqueadaPorLotacao = bloqueadaPorLotacao;
    }

    public void setStatusOportunidade(String statusOportunidade) {
        this.statusOportunidade = statusOportunidade;
    }

    public void setOfereceCertificado(Boolean ofereceCertificado) {
        this.ofereceCertificado = ofereceCertificado;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setExigeDocumentos(Boolean exigeDocumentos) {
        this.exigeDocumentos = exigeDocumentos;
    }

//metodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Oportunidade outra)) {
            return false;
        }
        return idOportunidade != null && idOportunidade.equals(outra.idOportunidade);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
