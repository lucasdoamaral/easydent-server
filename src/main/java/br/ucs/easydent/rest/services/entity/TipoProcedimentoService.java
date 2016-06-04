package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.TipoProcedimentoSession;
import br.ucs.easydent.model.entity.TipoProcedimento;

@Path("/tiposprocedimento")
public class TipoProcedimentoService extends EntityService<TipoProcedimento> {

	@EJB
	private TipoProcedimentoSession tipoProcedimentoSession;
	
	@Override
	protected EntityEJB<TipoProcedimento> getEJB() {
		return tipoProcedimentoSession;
	}

}
