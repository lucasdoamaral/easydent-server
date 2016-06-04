package br.ucs.easydent.rest.services.entity;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.ucs.easydent.ejb.session.EntityEJB;
import br.ucs.easydent.ejb.session.EspecialidadeSession;
import br.ucs.easydent.model.entity.Especialidade;

@Path("/especialidades")
public class EspecialidadeService extends EntityService<Especialidade> {

	@EJB
	private EspecialidadeSession especialidadeSession;
	
	@Override
	protected EntityEJB<Especialidade> getEJB() {
		return especialidadeSession;
	}

}
