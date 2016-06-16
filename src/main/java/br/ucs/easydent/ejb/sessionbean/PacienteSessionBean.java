package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.ejb.session.PacienteSession;
import br.ucs.easydent.model.entity.Estabelecimento;
import br.ucs.easydent.model.entity.Paciente;

@Stateless
public class PacienteSessionBean extends BaseSessionBean implements PacienteSession {

	public Paciente buscarPorId(Long id) {
		return em.find(Paciente.class, id);
	}

	public List<Paciente> buscarPorEstabelecimento(Estabelecimento estabelecimento, QueryParams params) {

		StringBuilder select = new StringBuilder();
		select.append(" SELECT e FROM Paciente AS e ");
		select.append(" WHERE e.estabelecimento = :estabelecimento ");

		Query query = em.createQuery(select.toString());
		query.setParameter("estabelecimento", estabelecimento);

		return (List<Paciente>) query.getResultList();

	}

	@Deprecated
	public List<Paciente> buscarTodos(QueryParams params) {
		throw new NotImplementedException("Não é possível selecionar todos os pacientes, por questões de segurança.");
		// String queryString = "SELECT e FROM Paciente AS e";
		// if (params.getOrdenacao() != null) {
		// queryString += " ORDER BY e." + params.getOrdenacao();
		// }
		//
		// Query query = em.createQuery(queryString);
		// Util.checkPagination(query, params);
		//
		// return query.getResultList();
	}

	public Paciente salvar(Paciente entidade) {

		// Se for paciente novo
		if (entidade.getId() == null) {

			// Deve buscar o próximo número para o estabelecimento
			Integer proximoCodigo = getProximoCodigoPacienteEstabelecimento(entidade.getEstabelecimento().getId());
			entidade.setCodigo(proximoCodigo);
		}

		return em.merge(entidade);
	}

	private Integer getProximoCodigoPacienteEstabelecimento(Long estabelecimentoId) {

		Query query = em
				.createQuery("SELECT MAX(e.codigo) FROM Paciente AS e WHERE e.estabelecimento.id = :estabelecimentoId");
		query.setParameter("estabelecimentoId", estabelecimentoId);

		Object resultado = query.getSingleResult();
		return resultado == null ? 1 : ((Integer) resultado) + 1;

	}

	public void excluir(Long id) {
		Paciente paciente = em.find(Paciente.class, id);
		if (paciente != null) {
			em.remove(paciente);
		}
	}

	public List<Paciente> buscarPorFiltro(BaseFilter<Paciente> filtro) {
		// TODO Criar método buscarPorFiltro em PacienteSessionBean
		throw new NotImplementedException("PacienteSessionBean/buscarPorFiltro");
	}

}
