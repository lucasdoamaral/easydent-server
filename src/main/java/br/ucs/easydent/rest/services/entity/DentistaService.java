package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.DentistaSession;
import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.model.entity.Dentista;

@Path("/dentistas")
public class DentistaService extends EntityService<Dentista> {

	@EJB
	private DentistaSession dentistaSession;

	@Override
	protected EntityEJB<Dentista> getEJB() {
		return dentistaSession;
	}


}
