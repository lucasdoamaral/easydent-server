package br.ucs.easydent.rest.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.session.EntityEJB;
import br.ucs.easydent.session.EspecialidadeSession;
import br.ucs.easydental.model.Especialidade;

@Path("/especialidades")
public class EspecialidadeService extends EntityService<Especialidade> {

	@EJB
	private EspecialidadeSession especialidadeSession;
	
	@Override
	protected EntityEJB<Especialidade> getEJB() {
		return especialidadeSession;
	}

}
