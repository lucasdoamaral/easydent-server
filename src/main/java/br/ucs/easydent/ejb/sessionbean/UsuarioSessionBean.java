package br.ucs.easydent.ejb.sessionbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.NotImplementedException;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.util.Util;
import br.ucs.easydent.ejb.session.UsuarioSession;
import br.ucs.easydent.model.entity.Usuario;

@Stateless
public class UsuarioSessionBean extends BaseSessionBean implements UsuarioSession {

	public Usuario buscarPorId(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		detachUsuario(usuario);
		return usuario;
	}

	public List<Usuario> buscarTodos(QueryParams params) {

		String queryString = "SELECT e FROM Usuario AS e";
		if (params.getOrdenacao()!=null){
			queryString += " ORDER BY e." + params.getOrdenacao();
		}
		
		Query query = em.createQuery(queryString);
		Util.checkPagination(query, params);
		
		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		detachUsuarios(usuarios);

		return usuarios;
	}

	public Usuario salvar(Usuario entidade) {
		return em.merge(entidade);
	}

	public void excluir(Long id) {
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario != null) {
			em.remove(usuario);
		}
	}

	public List<Usuario> buscarPorFiltro(BaseFilter<Usuario> filtro) {
		// TODO Criar método buscarPorFiltro em UsuarioSessionBean
		throw new NotImplementedException("UsuarioSessionBean/buscarPorFiltro");
	}

	private void detachUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			detachUsuario(usuario);
		}
	}

	private void detachUsuario(Usuario usuario) {
		// nada para carregar
	}

}
