package br.ucs.easydent.model.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ucs.easydent.model.enums.SituacaoConsultaEnum;
import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Consulta implements EntidadeComEstabelecimento {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "consulta", sequenceName = "consulta_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta")
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Dentista dentista;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@Column(nullable = false)
	private Calendar data;

	@Column
	private Calendar dataFinal;

	private String hora;

	private Integer duracaoMinutos;

	private Boolean diaCompleto;

	@Column(nullable = false)
	private Calendar dataCriacao;

	private Boolean confirmada;

	@ManyToOne
	@JoinColumn
	private Usuario confirmadoPor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario criadoPor;

	@Column(nullable = false)
	private Integer fgSituacaoConsultaEnum;

	@Column(nullable = false)
	private String procedimento;

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

	public Integer getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public Boolean getDiaCompleto() {
		return diaCompleto;
	}

	public void setDiaCompleto(Boolean diaCompleto) {
		this.diaCompleto = diaCompleto;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
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

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Integer getFgSituacaoConsultaEnum() {
		return fgSituacaoConsultaEnum;
	}

	public void setFgSituacaoConsultaEnum(Integer fgSituacaoConsultaEnum) {
		this.fgSituacaoConsultaEnum = fgSituacaoConsultaEnum;
	}

	public void setSituacaoConsultaEnum(SituacaoConsultaEnum situacaoConsultaEnum) {
		this.fgSituacaoConsultaEnum = situacaoConsultaEnum != null ? situacaoConsultaEnum.getId() : null;
	}

	@JsonIgnore
	public SituacaoConsultaEnum getSituacaoConsultaEnum() {
		return SituacaoConsultaEnum.getById(fgSituacaoConsultaEnum);
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}

	public String getHora() {
		return hora;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public Estabelecimento getEstabelecimento() {
		if (dentista != null) {
			return dentista.getEstabelecimento();
		}
		if (paciente != null) {
			return paciente.getEstabelecimento();
		}
		return null;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setData(Calendar data) {
		setData(data, true);
	}

	public void setData(Calendar data, boolean notNull) {
		if (data != null || !notNull) {
			this.data = data;
			if (duracaoMinutos != null) {
				Calendar _data = (Calendar) data.clone();
				_data.add(Calendar.MINUTE, duracaoMinutos);
				this.dataFinal = _data;
			}
		}
	}

	public void setDuracaoMinutos(Integer duracaoMinutos) {
		setDuracaoMinutos(duracaoMinutos, true);
	}

	public void setDuracaoMinutos(Integer duracaoMinutos, boolean notNull) {
		if (duracaoMinutos != null || !notNull) {
			this.duracaoMinutos = duracaoMinutos;
			if (data != null) {
				Calendar _dataFinal = (Calendar) data.clone();
				_dataFinal.add(Calendar.MINUTE, duracaoMinutos);
				this.dataFinal = _dataFinal;
			}
		}
	}

	public void setDataFinal(Calendar dataFinal) {
		setDataFinal(dataFinal, true);
	}

	public void setDataFinal(Calendar dataFinal, boolean notNull) {
		if (dataFinal != null || !notNull) {
			this.dataFinal = dataFinal;
			if (duracaoMinutos != null) {
				Calendar _data = (Calendar) dataFinal.clone();
				_data.add(Calendar.MINUTE, duracaoMinutos * -1);
				this.dataFinal = _data;
			}
		}
	}

}
