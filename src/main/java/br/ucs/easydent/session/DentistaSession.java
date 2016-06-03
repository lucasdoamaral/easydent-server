package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Dentista;

@Remote
public interface DentistaSession  extends EntityEJB<Dentista> {

}
