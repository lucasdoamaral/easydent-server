package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.LaboratorioSession;
import br.ucs.easydental.model.Laboratorio;

@Path("laboratorios")
public class LaboratorioService extends EntityService<Laboratorio> {

	@EJB
	private LaboratorioSession laboratorioSession;
	
	@Override
	protected EntityEJB<Laboratorio> getEJB() {
		return laboratorioSession;
	}

}
