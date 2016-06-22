package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Consulta;

@Remote
public interface ConsultaSession  extends EntityEJB<Consulta> {
	
}
