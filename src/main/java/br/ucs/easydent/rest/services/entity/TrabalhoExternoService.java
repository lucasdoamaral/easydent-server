package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.TrabalhoExternoSession;
import br.ucs.easydent.model.entity.TrabalhoExterno;

@Path("/trabalhosexternos")
public class TrabalhoExternoService extends EntityService<TrabalhoExterno> {

	@EJB
	private TrabalhoExternoSession trabalhoExternoSession;
	
	@Override
	protected EntityEJB<TrabalhoExterno> getEJB() {
		return trabalhoExternoSession;
	}

}
