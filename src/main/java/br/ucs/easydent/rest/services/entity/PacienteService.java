package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.PacienteSession;
import br.ucs.easydent.model.entity.Paciente;

@Path("/pacientes")
public class PacienteService extends EntityService<Paciente> {

	@EJB
	private PacienteSession pacienteSession;

	@Override
	protected EntityEJB<Paciente> getEJB() {
		return pacienteSession;
	}

	@OPTIONS
	public void options() {
		// System.out.println("Calling OPTIONS HTTP method.");
	}

}
