package br.ucs.easydent.ejb.sessionbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.dto.filtro.ConsultaFilter;
import br.ucs.easydent.app.exceptions.HorarioNaoDisponivelException;
import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.ConsultaSession;
import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Especialidade;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.SituacaoConsultaEnum;
import br.ucs.easydent.security.PermissaoPorEstabelecimento;

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

	public Consulta salvar(Usuario usuario, Consulta entidade) throws HorarioNaoDisponivelException {

		// Se for uma nova consulta
		if (entidade.getId() == null) {
			entidade.setDataCriacao(Calendar.getInstance());
			entidade.setCriadoPor(usuario);
			entidade.setSituacaoConsultaEnum(SituacaoConsultaEnum.AGENDADA);
		}
		// entidade.setData(getDataHora(entidade));

		validarDisponibilidadeAgenda(entidade);

		return detach(em.merge(entidade));
	}

	private void validarDisponibilidadeAgenda(Consulta consulta) throws HorarioNaoDisponivelException {

		StringBuilder queryString = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		queryString.append(" SELECT 1 FROM CONSULTA AS C ");
		queryString.append(" WHERE 1=1 ");

		queryString.append(" AND c.dentista_id = :dentistaId ");
		params.put("dentistaId", consulta.getDentista().getId());

		queryString.append(" AND c.fgSituacaoConsultaEnum != :statusDiferente ");
		params.put("statusDiferente", SituacaoConsultaEnum.CANCELADA.getId());

		queryString.append(" AND ((:dataInicial > C.DATA AND :dataInicial < C.DATAFINAL) ");
		queryString.append(" OR (:dataFinal > C.DATA AND :dataFinal < C.DATAFINAL) ");
		queryString.append(" OR (:dataInicial < C.DATA AND :dataFinal > C.DATA) ");
		queryString.append(" OR (:dataInicial > C.DATA AND :dataFinal < C.DATA)) ");
		params.put("dataInicial", consulta.getData());
		params.put("dataFinal", consulta.getDataFinal());

		if (consulta.getId() != null) {
			queryString.append(" AND C.ID != :consultaId ");
			params.put("consultaId", consulta.getId());
		}

		Query query = em.createNativeQuery(queryString.toString());
		Util.setParams(query, params);
		query.setMaxResults(1);

		List<Object> retorno = query.getResultList();
		if (retorno != null && !retorno.isEmpty()) {
			throw new HorarioNaoDisponivelException();
		}

	}

	public void excluir(Usuario usuario, Long id) {
		Consulta consulta = this.buscarPorId(usuario, id);
		if (consulta != null) {
			em.remove(consulta);
		}
	}

	public List<Consulta> buscarPorFiltro(Usuario usuario, Options options, BaseFilter<Consulta> filtroBase)
			throws ProblemaPermissaoException {

		ConsultaFilter filtro = (ConsultaFilter) filtroBase;

		StringBuilder queryString = new StringBuilder();
		Map<String, Object> params = new HashMap<>();

		queryString.append(" SELECT e FROM Consulta AS e ");
		queryString.append(" WHERE 1=1 ");

		queryString.append(" AND e.dentista.estabelecimento = :estabelecimentoUsuario ");
		params.put("estabelecimentoUsuario", usuario.getEstabelecimento());

		if (filtro.getDataMaiorDoQue() != null) {
			queryString.append(" AND e.data > :dataMaiorDoQue ");
			params.put("dataMaiorDoQue", filtro.getDataMaiorDoQue());
		}

		if (filtro.getDataMenorDoQue() != null) {
			queryString.append(" AND e.data < :dataMenorDoQue ");
			params.put("dataMenorDoQue", filtro.getDataMenorDoQue());
		}

		if (filtro.getPaciente() != null) {
			queryString.append(" AND e.paciente = :paciente ");
			params.put("paciente", filtro.getPaciente());
		}

		if (filtro.getSituacaoConsulta() != null) {
			queryString.append(" AND e.fgSituacaoConsultaEnum = :situacaoConsultaEnum");
			params.put("situacaoConsultaEnum", filtro.getSituacaoConsulta().getId());
		}

		if (filtro.getSituacoes() != null && !filtro.getSituacoes().isEmpty()) {
			queryString.append(" AND (");
			Iterator<SituacaoConsultaEnum> situacoes = filtro.getSituacoes().iterator();
			Integer i = 0;
			while (situacoes.hasNext()) {
				SituacaoConsultaEnum situacao = situacoes.next();
				queryString.append(" e.fgSituacaoConsultaEnum = :situacaoConsultaEnum" + i + " ");
				params.put("situacaoConsultaEnum" + i, situacao.getId());
				i++;

				if (situacoes.hasNext()) {
					queryString.append(" OR ");
				}
			}
			queryString.append(" ) ");
		}

		if (filtro.getData() != null) {
			queryString.append(" AND YEAR(e.data) = :anoData AND MONTH(e.data) = :mesData AND DAY(e.data) = :diaData ");
			params.put("anoData", filtro.getData().get(Calendar.YEAR));
			params.put("mesData", filtro.getData().get(Calendar.MONTH) + 1);
			params.put("diaData", filtro.getData().get(Calendar.DAY_OF_MONTH));
		}

		if (filtro.getDentista() != null) {
			queryString.append(" AND e.dentista = :dentista ");
			params.put("dentista", filtro.getDentista());
		}

		// Check permissions
		switch (usuario.getTipoUsuarioEnum()) {

		case ADMIN:
			break;

		// Pode ver as próprias consultas
		case DENTISTA:
			queryString.append(" AND e.dentista = :dentista ");
			params.put("dentista", usuario.getDentista());
			break;

		// Pode ver as consultas do estabelecimento
		case GERENTE:
		case SECRETARIA:
			queryString.append(" AND e.dentista.estabelecimento = :estabelecimentoDentista ");
			params.put("estabelecimentoDentista", usuario.getEstabelecimento());
			break;

		// Pode ver as próprias consultas
		case PACIENTE:
			queryString.append(" AND e.paciente = :paciente ");
			params.put("estabelecimentoDentista", usuario.getPaciente());
			break;

		default:
			throw new ProblemaPermissaoException("Usuário com tipo incorreto");
		}

		if (options.getOrdenacao() != null) {
			queryString.append(" ORDER BY e." + options.getOrdenacao());
		}

		Query query = em.createQuery(queryString.toString());
		Util.checkPagination(query, options);
		Util.setParams(query, params);

		List<Consulta> consultas = (List<Consulta>) query.getResultList();
		detach(consultas);

		PermissaoPorEstabelecimento.checkIfCanGetsEntity(usuario, consultas);

		return consultas;

	}

	private void detach(List<Consulta> consultas) {
		for (Consulta consulta : consultas) {
			detach(consulta);
		}
	}

	private Consulta detach(Consulta consulta) {
		Dentista dentista = consulta.getDentista();
		dentista.setEspecialidades(new ArrayList<Especialidade>(dentista.getEspecialidades()));
		dentista.setHorarios(new ArrayList<>(dentista.getHorarios()));
		dentista.setHorariosEspeciais(new ArrayList<>(dentista.getHorariosEspeciais()));
		consulta.setDentista(dentista);
		return consulta;
	}

}
