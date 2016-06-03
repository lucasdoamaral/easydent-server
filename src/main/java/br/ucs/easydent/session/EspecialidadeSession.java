package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Especialidade;

@Remote
public interface EspecialidadeSession  extends EntityEJB<Especialidade> {

}
