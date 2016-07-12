package br.ucs.easydent.ejb.session;

import java.util.List;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.app.exceptions.EasydentException;
import br.ucs.easydent.ejb.sessionbean.Options;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.intf.Entidade;

public interface EntityEJB<T extends Entidade> {

	public T buscarPorId(Usuario usuario, Long id) throws EasydentException;

	public List<T> buscarTodos(Usuario usuario, Options params) throws EasydentException;

	public T salvar(Usuario usuario, T entidade) throws EasydentException;

	public void excluir(Usuario usuario, Long id) throws EasydentException;

	public List<T> buscarPorFiltro(Usuario usuario, Options params, BaseFilter<T> filtro) throws EasydentException;

}
