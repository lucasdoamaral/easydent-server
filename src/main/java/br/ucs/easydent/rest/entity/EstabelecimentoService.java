package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.EstabelecimentoSession;
import br.ucs.easydental.model.Estabelecimento;

public class EstabelecimentoService extends EntityService<Estabelecimento> {

	@EJB
	private EstabelecimentoSession estabelecimentoSession;
	
	@Override
	protected EntityEJB<Estabelecimento> getEJB() {
		return estabelecimentoSession;
	}

}
