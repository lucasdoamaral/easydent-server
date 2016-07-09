package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.LaboratorioSession;
import br.ucs.easydent.model.entity.Laboratorio;

@Path("laboratorios")
public class LaboratorioService extends EntityService<Laboratorio> {

	@EJB
	private LaboratorioSession laboratorioSession;
	
	@Override
	protected EntityEJB<Laboratorio> getEJB() {
		return laboratorioSession;
	}
	
	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}


}
