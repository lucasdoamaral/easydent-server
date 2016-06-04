package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Especialidade;

@Remote
public interface EspecialidadeSession  extends EntityEJB<Especialidade> {

}
