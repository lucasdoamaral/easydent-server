package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.PacienteSession;
import br.ucs.easydental.model.Paciente;

@Path("/pacientes")
public class PacienteService extends EntityService<Paciente> {

	@EJB
	private PacienteSession pacienteSession;

	@Override
	protected EntityEJB<Paciente> getEJB() {
		return pacienteSession;
	}
	
}
