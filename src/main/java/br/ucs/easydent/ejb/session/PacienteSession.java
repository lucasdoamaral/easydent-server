package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Paciente;

@Remote
public interface PacienteSession  extends EntityEJB<Paciente> {

}
