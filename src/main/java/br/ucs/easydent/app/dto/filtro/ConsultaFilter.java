package br.ucs.easydent.app.dto.filtro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Paciente;
import br.ucs.easydent.model.enums.SituacaoConsultaEnum;

public class ConsultaFilter extends BaseFilter<Consulta> {

	private static final long serialVersionUID = 1L;

	private Dentista dentista;

	private Calendar dataMaiorDoQue;
	private Calendar dataMenorDoQue;
	private SituacaoConsultaEnum situacaoConsulta;

	private List<SituacaoConsultaEnum> situacoes = new ArrayList<>();

	private Paciente paciente;

	private Calendar data;

	public ConsultaFilter() {
		// TODO Auto-generated constructor stub
	}

	public Dentista getDentista() {
		return dentista;
	}

	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}

	public Calendar getDataMaiorDoQue() {
		return dataMaiorDoQue;
	}

	public void setDataMaiorDoQue(Calendar dataMaiorDoQue) {
		this.dataMaiorDoQue = dataMaiorDoQue;
	}

	public Calendar getDataMenorDoQue() {
		return dataMenorDoQue;
	}

	public void setDataMenorDoQue(Calendar dataMenorDoQue) {
		this.dataMenorDoQue = dataMenorDoQue;
	}

	public SituacaoConsultaEnum getSituacaoConsulta() {
		return situacaoConsulta;
	}

	public void setSituacaoConsulta(SituacaoConsultaEnum situacaoConsulta) {
		this.situacaoConsulta = situacaoConsulta;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<SituacaoConsultaEnum> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<SituacaoConsultaEnum> situacoes) {
		this.situacoes = situacoes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
