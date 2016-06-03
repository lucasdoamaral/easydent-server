package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Laboratorio;

@Remote
public interface LaboratorioSession extends EntityEJB<Laboratorio> {

}
