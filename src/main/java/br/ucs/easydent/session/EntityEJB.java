package br.ucs.easydent.session;

import java.util.List;

import br.ucs.easydent.filtro.BaseFilter;
import br.ucs.easydent.sessionbean.QueryParams;
import br.ucs.easydental.model.intf.Entidade;

public interface EntityEJB<T extends Entidade> {

	public T buscarPorId(Long id);

	public List<T> buscarTodos(QueryParams params);

	public T salvar(T entidade);

	public void excluir(Long id);

	public List<T> buscarPorFiltro(BaseFilter<T> filtro);
	
}
