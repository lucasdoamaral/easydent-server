package br.ucs.easydent.ejb.sessionbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.dto.filtro.ConsultaFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Especialidade;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class ConsultaSessionBean extends BaseSessionBean implements ConsultaSession {

	public Consulta buscarPorId(Usuario usuario, Long id) {
		Consulta consulta = em.find(Consulta.class, id);
		detach(consulta);
		return consulta;
	}

	public List<Consulta> buscarTodos(Usuario usuario, Options params) {
		String queryString = "SELECT e FROM Consulta AS e";
		if (params.getOrdenacao() != null) {
			queryString += " ORDER BY e." + params.getOrdenacao();
		}

		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);

		List<Consulta> consultas = (List<Consulta>) query.getResultList();
		detach(consultas);

		return consultas;
	}

	public Consulta salvar(Usuario usuario, Consulta entidade) {
		return em.merge(entidade);
	}

	public void excluir(Usuario usuario, Long id) {
		Consulta consulta = this.buscarPorId(usuario, id);
		if (consulta != null) {
			em.remove(consulta);
		}
	}

	public List<Consulta> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Consulta> filtroBase) {

		ConsultaFilter filtro = (ConsultaFilter) filtroBase;

		StringBuilder queryString = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		queryString.append(" SELECT e FROM Consulta AS e ");
		queryString.append(" WHERE 1=1 ");

		if (filtro.getDataMaiorDoQue() != null) {
			queryString.append(" AND e.data > :dataMaiorDoQue ");
			params.put("dataMaiorDoQue", filtro.getDataMaiorDoQue());
		}

		if (filtro.getDataMenorDoQue() != null) {
			queryString.append(" AND e.data < :dataMenorDoQue ");
			params.put("dataMenorDoQue", filtro.getDataMaiorDoQue());
		}

		if (filtro.getSituacaoConsulta() != null) {
			queryString.append(" AND e.fgSituacaoConsultaEnum = :situacaoConsultaEnum");
			params.put("situacaoConsultaEnum", filtro.getSituacaoConsulta().getId());
		}

		if (filtro.getDentista() != null) {
			queryString.append(" AND e.dentista = :dentista ");
			params.put("dentista", filtro.getDentista());
		}

		if (options.getOrdenacao() != null) {
			queryString.append(" ORDER BY e." + options.getOrdenacao());
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, options);
		Util.setParams(query, params);

		List<Consulta> consultas = (List<Consulta>) query.getResultList();
		detach(consultas);
		return consultas;

	}

	private void detach(List<Consulta> consultas) {
		for (Consulta consulta : consultas) {
			detach(consulta);
		}
	}

	private void detach(Consulta consulta) {
		Dentista dentista = consulta.getDentista();
		dentista.setEspecialidades(new ArrayList<Especialidade>(dentista.getEspecialidades()));
		dentista.setHorarios(new ArrayList<>(dentista.getHorarios()));
		dentista.setHorariosEspeciais(new ArrayList<>(dentista.getHorariosEspeciais()));
		consulta.setDentista(dentista);
	}

}
