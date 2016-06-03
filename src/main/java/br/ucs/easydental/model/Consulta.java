package br.ucs.easydental.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.enums.SituacaoConsultaEnum;
import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Consulta implements Entidade {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "consulta", sequenceName = "consulta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta")
	private Long id;
	
	@ManyToOne
	private Dentista dentista;
	
	@ManyToOne
	private Paciente paciente;
	
	private Calendar data;
	
	private Integer duracaoMinutos;
	
	private Boolean diaCompleto;
	
	private Calendar dataCriacao;
	
	private Boolean confirmada;
	
	@ManyToOne
	private Usuario confirmadoPor;
	
	@ManyToOne
	private Usuario criadoPor;
	
	private Integer fgSituacaoConsultaEnum;
	
	private String procedimento;
	
	@ManyToOne
	private Agenda agenda;
	
	@Override
	public Long getId() {
		return id;
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Boolean getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Boolean confirmada) {
		this.confirmada = confirmada;
	}

	public Usuario getConfirmadoPor() {
		return confirmadoPor;
	}

	public void setConfirmadoPor(Usuario confirmadoPor) {
		this.confirmadoPor = confirmadoPor;
	}

	public SituacaoConsultaEnum getSituacaoConsultaEnum () {
		return SituacaoConsultaEnum.getById(getFgSituacaoConsultaEnum());
	}

	public Integer getFgSituacaoConsultaEnum() {
		return fgSituacaoConsultaEnum;
	}

	public void setFgSituacaoConsultaEnum(Integer fgSituacaoConsultaEnum) {
		this.fgSituacaoConsultaEnum = fgSituacaoConsultaEnum;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public void setDuracaoMinutos(Integer duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}

	public Boolean getDiaCompleto() {
		return diaCompleto;
	}

	public void setDiaCompleto(Boolean diaCompleto) {
		this.diaCompleto = diaCompleto;
	}

}
