package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.DentistaSession;
import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydental.model.Dentista;

@Path("/dentistas")
public class DentistaService extends EntityService<Dentista> {

	@EJB
	private DentistaSession dentistaSession;

	@Override
	protected EntityEJB<Dentista> getEJB() {
		return dentistaSession;
	}


}
