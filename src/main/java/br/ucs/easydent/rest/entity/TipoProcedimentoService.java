package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.TipoProcedimentoSession;
import br.ucs.easydental.model.TipoProcedimento;

@Path("/tiposprocedimento")
public class TipoProcedimentoService extends EntityService<TipoProcedimento> {

	@EJB
	private TipoProcedimentoSession tipoProcedimentoSession;
	
	@Override
	protected EntityEJB<TipoProcedimento> getEJB() {
		return tipoProcedimentoSession;
	}

}
