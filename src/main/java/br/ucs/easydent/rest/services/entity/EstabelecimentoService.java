package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.EstabelecimentoSession;
import br.ucs.easydent.model.entity.Estabelecimento;

public class EstabelecimentoService extends EntityService<Estabelecimento> {

	@EJB
	private EstabelecimentoSession estabelecimentoSession;
	
	@Override
	protected EntityEJB<Estabelecimento> getEJB() {
		return estabelecimentoSession;
	}

}
