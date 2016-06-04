package br.ucs.easydent.ejb.session;

import java.util.List;

import br.ucs.easydent.app.dto.filtro.BaseFilter;
import br.ucs.easydent.ejb.sessionbean.QueryParams;
import br.ucs.easydent.model.intf.Entidade;

public interface EntityEJB<T extends Entidade> {

	public T buscarPorId(Long id);

	public List<T> buscarTodos(QueryParams params);

	public T salvar(T entidade);

	public void excluir(Long id);

	public List<T> buscarPorFiltro(BaseFilter<T> filtro);
	
}
