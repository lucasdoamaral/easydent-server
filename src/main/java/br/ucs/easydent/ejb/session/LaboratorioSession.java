package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Laboratorio;

@Remote
public interface LaboratorioSession extends EntityEJB<Laboratorio> {

}
