package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.TrabalhoExternoSession;
import br.ucs.easydental.model.TrabalhoExterno;

@Path("/trabalhosexternos")
public class TrabalhoExternoService extends EntityService<TrabalhoExterno> {

	@EJB
	private TrabalhoExternoSession trabalhoExternoSession;
	
	@Override
	protected EntityEJB<TrabalhoExterno> getEJB() {
		return trabalhoExternoSession;
	}

}
