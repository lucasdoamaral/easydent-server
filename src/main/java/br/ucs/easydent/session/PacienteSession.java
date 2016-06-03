package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Paciente;

@Remote
public interface PacienteSession  extends EntityEJB<Paciente> {

}
